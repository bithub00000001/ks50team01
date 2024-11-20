package ksmart.ks50team01.documentation.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.Resource;

import ksmart.ks50team01.common.exception.DocumentProcessException;
import ksmart.ks50team01.documentation.dto.response.DocumentApiResponse;
import ksmart.ks50team01.documentation.dto.response.DocumentTreeResponse;
import ksmart.ks50team01.documentation.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 대기오염공정시험기준 문서 관리를 위한 컨트롤러
 * 문서 조회, 업데이트, 다운로드 기능을 제공
 *
 * @author Hanbit Kang
 * @version 1.0
 * @since 2024-11-20
 */
@Controller
@RequestMapping({"/documentation/documents", "/documents"})
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

	private final DocumentService documentService;

	/**
	 * 문서 트리 구조 페이지 조회
	 * storagePath 디렉토리의 문서들을 트리 구조로 표시
	 *
	 * @param model Spring MVC 모델 객체
	 * @return String 뷰 이름 (documentation/documents/tree)
	 */
	@GetMapping
	public String viewDocumentTree(Model model) {
		try {
			// 문서 트리 데이터 조회
			List<DocumentTreeResponse> treeData = documentService.getDocumentTree();
			model.addAttribute("documentTree", treeData);
			return "documentation/documents/tree";
		} catch (Exception e) {
			log.error("문서 트리 조회 중 오류 발생", e);
			model.addAttribute("error", "문서 트리를 불러오는 중 오류가 발생했습니다.");
			return "documentation/error/500";  // 에러 페이지로 리다이렉트
		}
	}

	@GetMapping("/api/tree")
	@ResponseBody
	public ResponseEntity<List<DocumentTreeResponse>> getDocumentTree() {
		try {
			List<DocumentTreeResponse> treeData = documentService.getDocumentTree();
			return ResponseEntity.ok(treeData);
		} catch (Exception e) {
			log.error("문서 트리 조회 중 오류 발생", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	/**
	 * 문서 업데이트 처리
	 * 법령정보 API를 통해 최신 문서를 다운로드하고 저장
	 * 동일한 해시값이 있는 경우 중복 저장 방지
	 *
	 * @return ResponseEntity<DocumentApiResponse> API 처리 결과
	 *         - 성공: 200 OK와 성공 메시지
	 *         - 실패: 500 Internal Server Error와 에러 메시지
	 */
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<DocumentApiResponse> updateDocuments() {
		try {
			documentService.processDocumentUpdate();
			return ResponseEntity.ok(DocumentApiResponse.success("문서가 성공적으로 업데이트되었습니다."));
		} catch (Exception e) {
			log.error("문서 업데이트 중 오류 발생", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(DocumentApiResponse.error("문서 업데이트 중 오류가 발생했습니다."));
		}
	}

	/**
	 * 문서 파일 다운로드
	 * 지정된 경로의 파일을 다운로드할 수 있는 응답 생성
	 *
	 * @param filePath 다운로드할 파일의 상대 경로
	 * @return ResponseEntity<Resource> 파일 다운로드 응답
	 *         - 성공: 200 OK와 파일 리소스
	 *         - 실패: DocumentProcessException 발생
	 * @throws DocumentProcessException 파일을 찾을 수 없거나 접근 권한이 없는 경우
	 */
	@GetMapping("/download/{*filePath}")
	public ResponseEntity<Resource> downloadFile(@PathVariable(name = "filePath") String filePath) {
		try {
			// URL 디코딩
			String decodedPath = URLDecoder.decode(filePath, StandardCharsets.UTF_8);
			log.info("파일 다운로드 요청 경로: {}", decodedPath);

			// 기본 검증
			if (decodedPath == null || decodedPath.isEmpty()) {
				throw new IllegalArgumentException("파일 경로는 필수입니다.");
			}

			// 경로 주입 공격 방지
			if (decodedPath.contains("..")) {
				throw new IllegalArgumentException("상위 디렉토리 접근은 허용되지 않습니다.");
			}

			// 경로 문자 검증 (한글, 영문, 숫자, 공백, 일부 특수문자 허용)
			if (!decodedPath.matches("^[\\w\\s가-힣\\-.,()\\[\\]/]+$")) {
				throw new IllegalArgumentException("허용되지 않는 문자가 포함되어 있습니다.");
			}

			// PDF 파일 확장자 검증
			if (!decodedPath.toLowerCase().endsWith(".pdf")) {
				throw new IllegalArgumentException("PDF 파일만 다운로드 가능합니다.");
			}

			log.info("최종 처리 경로: {}", decodedPath);
			return documentService.downloadFile(decodedPath);

		} catch (Exception e) {
			log.error("파일 다운로드 중 오류 발생: {}", filePath, e);
			throw new DocumentProcessException("파일 다운로드 실패", e);
		}
	}
}
