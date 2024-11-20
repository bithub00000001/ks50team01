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
 * 파일 다운로드 전용 서비스
 */
@Service
@Slf4j
public class FileDownloadService {

	/**
	 * 파일 다운로드
	 */
	public Path downloadFile(String fileUrl) {
		Path tempFile = null;
		HttpURLConnection conn = null;

		try {
			tempFile = Files.createTempFile("download_", ".zip");
			log.info("임시 파일 생성: {}", tempFile);

			URL url = new URL(getSecureUrl(fileUrl));
			conn = createConnection(url);

			int responseCode = conn.getResponseCode();
			log.info("서버 응답 코드: {}", responseCode);

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

	private String getSecureUrl(String url) {
		return url.startsWith("http://") ? url.replace("http://", "https://") : url;
	}

	private HttpURLConnection createConnection(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		// 브라우저 헤더 설정
		setRequestHeaders(conn);

		conn.setInstanceFollowRedirects(true);
		conn.setDoInput(true);
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(300000);

		return conn;
	}

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

	private boolean isRedirect(int responseCode) {
		return responseCode == HttpURLConnection.HTTP_MOVED_TEMP ||
			responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
			responseCode == HttpURLConnection.HTTP_SEE_OTHER;
	}

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

	private void logProgress(int contentLength, long totalBytesRead) {
		if (contentLength > 0 && totalBytesRead % (1024 * 1024) == 0) {
			double progress = (totalBytesRead * 100.0) / contentLength;
			log.info("다운로드 진행률: {:.1f}%, {}/{} MB",
				progress, totalBytesRead/(1024*1024), contentLength/(1024*1024));
		}
	}

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
