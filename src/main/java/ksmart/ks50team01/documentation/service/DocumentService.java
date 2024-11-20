package ksmart.ks50team01.documentation.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import ksmart.ks50team01.documentation.dto.response.DocumentApiResponse;
import ksmart.ks50team01.documentation.dto.response.DocumentFileResponse;
import ksmart.ks50team01.documentation.dto.response.DocumentTreeResponse;

/**
 * 문서 관리 서비스 인터페이스
 */
public interface DocumentService {

	/**
	 * 문서 업데이트 처리
	 * @return DocumentApiResponse 업데이트 결과
	 */
	DocumentApiResponse processDocumentUpdate();

	/**
	 * 문서 트리 구조 조회
	 * @return List<DocumentTreeResponse> 문서 트리 구조
	 */
	List<DocumentTreeResponse> getDocumentTree();

	/**
	 * 파일 다운로드
	 * @param filePath 파일 경로
	 * @return ResponseEntity<Resource> 파일 리소스
	 */
	ResponseEntity<Resource> downloadFile(String filePath);
}
