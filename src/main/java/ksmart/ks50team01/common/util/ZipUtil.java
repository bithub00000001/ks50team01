package ksmart.ks50team01.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

import ksmart.ks50team01.common.exception.DocumentProcessException;
import lombok.extern.slf4j.Slf4j;

/**
 * ZIP 파일 처리 유틸리티
 */
@Component
@Slf4j
public class ZipUtil {

	/**
	 * ZIP 파일 압축 해제
	 * @param zipFilePath ZIP 파일 경로
	 * @param destPath 압축 해제 대상 경로
	 * @return 압축 해제된 파일 목록
	 */
	public List<String> unzip(String zipFilePath, String destPath) {
		List<String> extractedFiles = new ArrayList<>();

		try (ZipInputStream zis = new ZipInputStream(
			new FileInputStream(zipFilePath), Charset.forName("EUC-KR"))) {

			File destDir = new File(destPath);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}

			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				File newFile = new File(destDir, entry.getName());

				// 디렉토리 경로 보안 검증
				String canonicalDestinationPath = destDir.getCanonicalPath();
				String canonicalEntryPath = newFile.getCanonicalPath();

				if (!canonicalEntryPath.startsWith(canonicalDestinationPath + File.separator)) {
					throw new SecurityException("ZIP 항목이 대상 디렉토리를 벗어납니다: " + entry.getName());
				}

				if (entry.isDirectory()) {
					newFile.mkdirs();
					continue;
				}

				// 상위 디렉토리 생성
				File parent = newFile.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}

				// 파일 복사
				try (FileOutputStream fos = new FileOutputStream(newFile)) {
					byte[] buffer = new byte[8192];
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
				}

				extractedFiles.add(newFile.getAbsolutePath());
				log.debug("파일 추출 완료: {}", newFile.getName());
			}

			log.info("ZIP 파일 압축 해제 완료. 추출된 파일 수: {}", extractedFiles.size());
			return extractedFiles;

		} catch (Exception e) {
			log.error("ZIP 파일 압축 해제 중 오류 발생: {}", zipFilePath, e);
			throw new DocumentProcessException("ZIP 파일 압축 해제 실패", e);
		}
	}
}
