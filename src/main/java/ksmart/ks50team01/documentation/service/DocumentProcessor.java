package ksmart.ks50team01.documentation.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ksmart.ks50team01.common.exception.DocumentProcessException;
import ksmart.ks50team01.common.util.FileHashUtil;
import ksmart.ks50team01.common.util.ZipUtil;
import ksmart.ks50team01.documentation.dto.DocumentProcessResult;
import ksmart.ks50team01.documentation.mapper.DocumentMapper;
import ksmart.ks50team01.documentation.model.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 문서 처리를 담당하는 컴포넌트
 * 법령정보 API 응답을 처리하고 ZIP 파일을 다운로드하여 압축 해제하는 기능 제공
 *
 * @author Hanbit Kang
 * @version 1.0
 * @since 2024-11-20
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentProcessor {

	private final FileHashUtil fileHashUtil;
	private final ZipUtil zipUtil;

	// 다운로드 로직 분리
	private final FileDownloadService fileDownloadService;

	private final DocumentMapper documentMapper;  // 추가

	@Value("${document.storage.path}")
	private String storagePath;

	/**
	 * API 응답으로부터 문서를 처리
	 * 1. ZIP 파일 URL 추출
	 * 2. 파일 다운로드 및 해시 계산
	 * 3. 해시값 비교 후 필요한 경우에만 압축 해제
	 *
	 * @param apiResponse API 응답 데이터
	 * @return DocumentProcessResult 처리 결과 객체
	 * @throws DocumentProcessException 문서 처리 중 발생하는 예외
	 */
	public DocumentProcessResult processDocument(Map<String, Object> apiResponse) {
		String zipUrl = extractZipFileUrl(apiResponse);
		ProcessResult result = downloadAndProcessZipFile(zipUrl);

		return DocumentProcessResult.builder()
			.fileName(extractFileName(zipUrl))
			.fileUrl(zipUrl)
			.fileHash(result.getFileHash())
			.isNewDocument(result.isNewDocument())
			.build();
	}

	/**
	 * API 응답에서 ZIP 파일 URL 추출
	 * 첨부파일 목록에서 .zip 확장자를 가진 파일의 URL을 찾아 반환
	 *
	 * @param apiResponse API 응답 데이터
	 * @return String ZIP 파일 URL
	 * @throws DocumentProcessException ZIP 파일을 찾을 수 없거나 추출 실패 시
	 */
	private String extractZipFileUrl(Map<String, Object> apiResponse) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> admRulService = (Map<String, Object>) apiResponse.get("AdmRulService");

			@SuppressWarnings("unchecked")
			Map<String, Object> attachFiles = (Map<String, Object>) admRulService.get("첨부파일");

			@SuppressWarnings("unchecked")
			List<String> fileUrls = (List<String>) attachFiles.get("첨부파일링크");

			@SuppressWarnings("unchecked")
			List<String> fileNames = (List<String>) attachFiles.get("첨부파일명");

			// ZIP 파일 검색
			for (int i = 0; i < fileNames.size(); i++) {
				if (fileNames.get(i).toLowerCase().endsWith(".zip")) {
					return fileUrls.get(i);
				}
			}
			throw new DocumentProcessException("ZIP 파일을 찾을 수 없습니다.");
		} catch (Exception e) {
			throw new DocumentProcessException("ZIP 파일 URL 추출 실패", e);
		}
	}

	/**
	 * URL에서 파일명 추출
	 *
	 * @param fileUrl 파일 URL
	 * @return String 파일명
	 */
	private String extractFileName(String fileUrl) {
		return fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
	}

	/**
	 * ZIP 파일 다운로드 및 처리
	 * 파일 다운로드, 해시 계산, 압축 해제를 수행
	 *
	 * @param zipUrl ZIP 파일 URL
	 * @return ProcessResult 처리 결과 (해시값, 신규 문서 여부)
	 * @throws DocumentProcessException 파일 처리 중 발생하는 예외
	 */
	private ProcessResult downloadAndProcessZipFile(String zipUrl) {
		Path tempFile = null;
		try {
			// 파일 다운로드
			tempFile = fileDownloadService.downloadFile(zipUrl);

			// 파일 해시 계산
			String fileHash = fileHashUtil.calculateHash(tempFile.toFile());
			log.info("파일 해시 계산 완료: {}", fileHash);

			// 해시값으로 기존 문서 검색
			DocumentEntity existingDocument = documentMapper.findByFileHash(fileHash);

			// 새로운 문서인 경우에만 압축 해제 수행
			if (existingDocument == null) {
				log.info("새로운 문서 발견. 압축 해제 시작");
				prepareDirectory(storagePath);
				List<String> extractedFiles = zipUtil.unzip(tempFile.toString(), storagePath);
				log.info("압축 해제 완료. 추출된 파일 수: {}", extractedFiles.size());
				return new ProcessResult(fileHash, true);
			} else {
				log.info("동일한 해시값의 문서가 이미 존재합니다: {}", fileHash);
				return new ProcessResult(fileHash, false);
			}

		} catch (Exception e) {
			log.error("문서 처리 중 오류 발생: {}", zipUrl, e);
			throw new DocumentProcessException("문서 처리 실패", e);
		} finally {
			cleanupTempFile(tempFile);
		}
	}

	/**
	 * 대상 디렉토리 준비
	 * 기존 디렉토리가 있으면 정리하고, 없으면 새로 생성
	 *
	 * @param path 디렉토리 경로
	 * @throws DocumentProcessException 디렉토리 처리 실패 시
	 */
	private void prepareDirectory(String path) {
		try {
			File destDir = new File(path);
			if (destDir.exists()) {
				FileUtils.cleanDirectory(destDir);
			} else {
				destDir.mkdirs();
			}
		} catch (IOException e) {
			throw new DocumentProcessException("디렉토리 준비 실패", e);
		}
	}

	/**
	 * 임시 파일 정리
	 * 가비지 컬렉션 유도 후 파일 삭제 시도
	 * 삭제 실패 시 JVM 종료 시점에 삭제되도록 예약
	 *
	 * @param tempFile 정리할 임시 파일
	 */
	private void cleanupTempFile(Path tempFile) {
		if (tempFile != null) {
			try {
				// 파일 핸들러 해제를 위한 GC 유도(Garbage Collection)
				System.gc();
				Thread.sleep(100);

				boolean deleted = Files.deleteIfExists(tempFile);
				if (!deleted) {
					tempFile.toFile().deleteOnExit();
					log.warn("임시 파일 즉시 삭제 실패. JVM 종료 시 삭제 예약됨: {}", tempFile);
				} else {
					log.info("임시 파일 삭제 완료: {}", tempFile);
				}
			} catch (Exception e) {
				log.warn("임시 파일 삭제 중 오류 발생. JVM 종료 시 삭제 예약됨: {}", tempFile, e);
				tempFile.toFile().deleteOnExit();
			}
		}
	}

	/**
	 * 처리 결과를 담는 내부 클래스
	 */
	@Data
	@AllArgsConstructor
	private static class ProcessResult {
		private String fileHash;
		private boolean newDocument;
	}

}
