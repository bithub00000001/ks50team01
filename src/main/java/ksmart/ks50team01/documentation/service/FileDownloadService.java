package ksmart.ks50team01.documentation.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ksmart.ks50team01.common.exception.DocumentProcessException;
import lombok.extern.slf4j.Slf4j;

/**
 * 원격 서버에서 파일을 다운로드하고 임시 저장하는 서비스
 *
 * @author Hanbit Kang
 * @version 1.0
 * @since 2024-11-20
 * <p>
 * 주요 기능:
 * - HTTP/HTTPS 프로토콜을 통한 파일 다운로드
 * - 리다이렉션 자동 처리
 * - 다운로드 진행률 모니터링
 * - 파일 무결성 검증
 */
@Service
@Slf4j
public class FileDownloadService {

	/**
	 * 지정된 URL에서 파일을 다운로드하여 임시 파일로 저장
	 *
	 * @param fileUrl 다운로드할 파일의 URL
	 * @return 다운로드된 파일의 Path
	 * @throws DocumentProcessException 다운로드 실패 시
	 */
	public Path downloadFile(String fileUrl) {
		Path tempFile;
		HttpURLConnection conn = null;

		try {
			tempFile = Files.createTempFile("download_", ".zip");
			log.info("임시 파일 생성: {}", tempFile);

			URL url = new URL(getSecureUrl(fileUrl));
			conn = createConnection(url);

			int responseCode = conn.getResponseCode();
			log.info("서버 응답 코드: {}", responseCode);

			// 리다이렉션 처리
			if (isRedirect(responseCode)) {
				String newUrl = conn.getHeaderField("Location");
				log.info("리다이렉션 감지. 새 URL: {}", newUrl);
				return downloadFile(newUrl);
			}

			if (responseCode == HttpURLConnection.HTTP_OK) {
				downloadToFile(conn, tempFile);
				return tempFile;
			}

			throw new DocumentProcessException("서버 응답 오류: " + responseCode);

		} catch (Exception e) {
			log.error("파일 다운로드 중 오류 발생: {}", fileUrl, e);
			throw new DocumentProcessException("파일 다운로드 실패", e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * HTTP URL을 HTTPS로 변환
	 * 보안 강화를 위해 HTTP 요청을 HTTPS로 자동 변환
	 */
	private String getSecureUrl(String url) {
		return url.startsWith("http://") ? url.replace("http://", "https://") : url;
	}

	/**
	 * HTTP 연결 설정
	 * 타임아웃, 리다이렉션, 입력 스트림 등 기본 설정
	 */
	private HttpURLConnection createConnection(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		// 브라우저 헤더 설정
		setRequestHeaders(conn);

		// 주요 연결 설정
		conn.setInstanceFollowRedirects(true); // HTTP 3xx 응답 코드(리다이렉션)를 받았을 때 자동으로 새로운 URL로 이동
		conn.setDoInput(true);                 // 서버로부터 응답 데이터를 읽을 수 있도록 입력 스트림을 활성화
		conn.setConnectTimeout(30000);         // 30초 연결 타임아웃
		conn.setReadTimeout(300000);           // 5분 읽기 타임아웃


		return conn;
	}

	/**
	 * 브라우저 에뮬레이션을 위한 HTTP 헤더 설정
	 * 웹사이트의 봇 차단을 우회하기 위한 설정
	 */
	private void setRequestHeaders(HttpURLConnection conn) {
		Map<String, String> headers = new HashMap<>();
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/119.0.0.0 Safari/537.36");
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.put("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
		headers.put("Connection", "keep-alive");
		headers.put("Upgrade-Insecure-Requests", "1");
		headers.put("Referer", "https://law.go.kr");

		headers.forEach(conn::setRequestProperty);
	}

	/**
	 * HTTP 리다이렉션 상태 코드 확인
	 */
	private boolean isRedirect(int responseCode) {
		return responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
		       responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
		       responseCode == HttpURLConnection.HTTP_SEE_OTHER;

	}

	/**
	 * 실제 파일 다운로드 수행
	 * 버퍼를 사용한 스트림 처리로 메모리 효율성 확보
	 *
	 * @param conn HTTP 연결
	 * @param tempFile 저장될 임시 파일 경로
	 */
	private void downloadToFile(HttpURLConnection conn, Path tempFile) throws IOException {
		int contentLength = conn.getContentLength();
		log.info("다운로드 시작. 예상 파일 크기: {} bytes", contentLength);

		try (InputStream is = conn.getInputStream();
			 FileOutputStream fos = new FileOutputStream(tempFile.toFile());
			 BufferedInputStream bis = new BufferedInputStream(is);
			 BufferedOutputStream bos = new BufferedOutputStream(fos)) {

			byte[] buffer = new byte[8192];
			int bytesRead;
			long totalBytesRead = 0;

			while ((bytesRead = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
				totalBytesRead += bytesRead;
				logProgress(contentLength, totalBytesRead);
			}

			bos.flush();
			validateDownload(contentLength, totalBytesRead, tempFile);
		}
	}

	/**
	 * 다운로드 진행률 로깅
	 * 1MB 단위로 진행상황 출력
	 */
	private void logProgress(int contentLength, long totalBytesRead) {
		if (contentLength > 0 && totalBytesRead % (1024 * 1024) == 0) {
			double progress = (totalBytesRead * 100.0) / contentLength;
			log.info("다운로드 진행률: {}%, {}/{} MB",
				String.format("%.1f", progress), totalBytesRead/(1024*1024), contentLength/(1024*1024));
		}
	}

	/**
	 * 다운로드 완료 후 파일 무결성 검증
	 *
	 * 검증 항목:
	 * 1. 파일이 비어있지 않은지 확인
	 * 2. 예상 크기와 실제 크기 일치 여부
	 * 3. 파일 시스템상 존재 여부
	 */
	private void validateDownload(int contentLength, long totalBytesRead, Path tempFile) throws IOException {
		if (totalBytesRead == 0) {
			throw new DocumentProcessException("다운로드된 파일이 비어있습니다.");
		}

		if (contentLength > 0 && totalBytesRead != contentLength) {
			throw new DocumentProcessException(
				String.format("파일 크기 불일치: 예상=%d, 실제=%d", contentLength, totalBytesRead));
		}

		if (!Files.exists(tempFile) || Files.size(tempFile) == 0) {
			throw new DocumentProcessException("다운로드된 파일이 유효하지 않습니다.");
		}
	}
}
