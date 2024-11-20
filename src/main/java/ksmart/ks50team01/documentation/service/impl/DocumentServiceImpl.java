package ksmart.ks50team01.documentation.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;

import ksmart.ks50team01.common.exception.DocumentProcessException;
import ksmart.ks50team01.common.util.DirectoryTreeUtil;
import ksmart.ks50team01.documentation.dto.DocumentProcessResult;
import ksmart.ks50team01.documentation.dto.response.DocumentApiResponse;
import ksmart.ks50team01.documentation.dto.response.DocumentTreeResponse;
import ksmart.ks50team01.documentation.mapper.DocumentMapper;
import ksmart.ks50team01.documentation.model.DocumentEntity;
import ksmart.ks50team01.documentation.service.ApiService;
import ksmart.ks50team01.documentation.service.DocumentProcessor;
import ksmart.ks50team01.documentation.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 문서 관리 서비스 구현체
 * 법령정보 API를 통해 대기오염공정시험기준 문서를 다운로드하고 관리하는 서비스
 *
 * @author Hanbit Kang
 * @version 1.0
 * @since 2024-11-20
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

	private final DocumentMapper documentMapper;
	private final WebClient documentWebClient;

	private final ApiService apiService;
	private final DocumentProcessor documentProcessor;

	// 디렉토리 생성 로직 분리
	private final DirectoryTreeUtil directoryTreeUtil;

	@Value("${document.storage.path}")
	private String storagePath;

	/**
	 * 문서 업데이트 메인 프로세스를 처리
	 * 1. API에서 최신 문서 정보를 조회
	 * 2. 문서를 다운로드하고 처리
	 * 3. 해시값 비교를 통해 중복 여부 확인 후 저장
	 *
	 * @return DocumentApiResponse API 처리 결과를 포함한 응답 객체
	 * @throws DocumentProcessException 문서 처리 중 발생하는 모든 예외
	 */
	@Override
	public DocumentApiResponse processDocumentUpdate() {
		try {
			// 1. API에서 최신 문서 정보 조회
			Map<String, Object> apiResponse = apiService.fetchLatestDocumentInfo();

			if (apiResponse != null) {
				// 2. 문서 처리
				DocumentProcessResult processResult = documentProcessor.processDocument(apiResponse);

				// 3. 문서 저장 여부 확인 및 처리
				return handleDocumentUpdate(processResult);
			}

			throw new DocumentProcessException("API 응답이 올바르지 않습니다.");
		} catch (Exception e) {
			log.error("문서 업데이트 중 오류 발생", e);
			throw new DocumentProcessException("문서 업데이트 실패", e);
		}
	}

	/**
	 * 문서 업데이트 결과를 처리하고 적절한 응답을 생성
	 * 동일한 해시값이 있는 경우 중복 저장을 방지
	 *
	 * @param processResult 문서 처리 결과 객체
	 * @return DocumentApiResponse 처리 결과에 따른 응답
	 */
	private DocumentApiResponse handleDocumentUpdate(DocumentProcessResult processResult) {
		// 해시값으로 기존 문서 검색
		DocumentEntity existingDocument = documentMapper.findByFileHash(processResult.getFileHash());

		if (existingDocument == null) {
			// 새로운 문서인 경우 저장
			saveNewDocument(processResult);
			return DocumentApiResponse.success("문서가 성공적으로 업데이트되었습니다.");
		} else {
			// 이미 존재하는 문서인 경우
			log.info("동일한 해시값의 문서가 이미 존재합니다: {}", processResult.getFileHash());
			return DocumentApiResponse.success("이미 최신 문서입니다.");
		}
	}

	/**
	 * 새로운 문서 이력을 데이터베이스에 저장
	 *
	 * @param processResult 저장할 문서 처리 결과
	 */
	private void saveNewDocument(DocumentProcessResult processResult) {
		DocumentEntity document = DocumentEntity.builder()
			.fileName(processResult.getFileName())
			.fileUrl(processResult.getFileUrl())
			.fileHash(processResult.getFileHash())
			.status(DocumentEntity.DocumentStatus.COMPLETED)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();

		documentMapper.insertDocumentHistory(document);
	}


	/**
	 * 저장된 문서들의 디렉토리 트리 구조를 생성
	 * DirectoryTreeUtil을 사용하여 파일 시스템의 계층 구조를 표현
	 *
	 * @return List<DocumentTreeResponse> 문서 트리 구조
	 */
	@Override
	public List<DocumentTreeResponse> getDocumentTree() {
		File rootDir = new File(storagePath);
		return directoryTreeUtil.createDirectoryTree(rootDir);
	}

	/**
	 * 문서 파일 다운로드 처리
	 * 파일 경로 검증, 리소스 로드, 응답 생성을 포함
	 *
	 * @param filePath 다운로드할 파일 경로
	 * @return ResponseEntity<Resource> 파일 다운로드 응답
	 * @throws DocumentProcessException 파일 처리 중 발생하는 예외
	 */
	@Override
	public ResponseEntity<Resource> downloadFile(String filePath) {
		try {
			Resource resource = loadFileResource(filePath);
			return createFileResponse(resource, filePath);
		} catch (Exception e) {
			log.error("파일 다운로드 중 오류 발생: {}", filePath, e);
			throw new DocumentProcessException("파일 다운로드 실패", e);
		}
	}

	/**
	 * 파일 시스템에서 리소스를 로드
	 * 파일의 존재 여부와 읽기 가능 여부를 검증
	 *
	 * @param filePath 파일 경로
	 * @return Resource 로드된 파일 리소스
	 * @throws IOException 파일 접근 실패시
	 */
	private Resource loadFileResource(String filePath) throws IOException {
		Path path = validateFilePath(filePath);
		Resource resource = new UrlResource(path.toUri());

		// 파일 접근 가능성 검증
		if (!resource.exists() || !resource.isReadable()) {
			throw new DocumentProcessException("파일을 찾을 수 없습니다: " + filePath);
		}

		return resource;
	}

	/**
	 * 파일 경로의 유효성을 검증
	 * Directory Traversal 공격 방지를 위한 경로 검증 포함
	 *
	 * @param filePath 검증할 파일 경로
	 * @return Path 검증된 경로
	 * @throws IOException 경로 처리 실패시
	 */
	private Path validateFilePath(String filePath) throws IOException {
		// storagePath와 상대 경로를 결합하여 전체 경로 생성
		Path fullPath = Paths.get(storagePath, filePath).normalize();
		Path storageDirPath = Paths.get(storagePath).normalize();

		log.debug("Storage Path: {}", storagePath);
		log.debug("Requested File Path: {}", filePath);
		log.debug("Full Path: {}", fullPath);

		// 파일이 storage 경로 내에 있는지 확인
		if (!fullPath.startsWith(storageDirPath)) {
			log.error("잘못된 파일 경로 접근 시도: {}", filePath);
			throw new DocumentProcessException("잘못된 파일 경로입니다.");
		}

		// 파일이 실제로 존재하는지 확인
		if (!Files.exists(fullPath)) {
			log.error("파일을 찾을 수 없음: {}", fullPath);
			throw new DocumentProcessException("파일을 찾을 수 없습니다: " + filePath);
		}

		return fullPath;
	}

	/**
	 * 파일 다운로드를 위한 HTTP 응답 생성
	 * Content-Type과 Content-Disposition 헤더 설정 포함
	 *
	 * @param resource 다운로드할 파일 리소스
	 * @param filePath 파일 경로
	 * @return ResponseEntity<Resource> HTTP 응답 객체
	 * @throws IOException 파일 처리 실패시
	 */
	private ResponseEntity<Resource> createFileResponse(Resource resource, String filePath)
		throws IOException {
		String contentType = determineContentType(filePath);
		String encodedFilename = encodeFileName(resource.getFilename());

		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + encodedFilename + "\"")
			.body(resource);
	}

	/**
	 * 파일의 MIME 타입을 결정
	 * 알 수 없는 타입의 경우 application/octet-stream으로 기본값 지정
	 *
	 * @param filePath 파일 경로
	 * @return String MIME 타입
	 */
	private String determineContentType(String filePath) throws IOException {
		String contentType = Files.probeContentType(Paths.get(filePath));
		return contentType != null ? contentType : "application/octet-stream";
	}

	/**
	 * 파일명을 UTF-8로 인코딩
	 * 다국어 파일명 지원을 위한 처리
	 *
	 * @param fileName 인코딩할 파일명
	 * @return String 인코딩된 파일명
	 */
	private String encodeFileName(String fileName) {
		return UriUtils.encode(fileName, StandardCharsets.UTF_8);
	}
}
