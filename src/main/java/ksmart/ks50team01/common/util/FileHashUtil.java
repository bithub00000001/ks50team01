package ksmart.ks50team01.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;

import ksmart.ks50team01.common.exception.DocumentProcessException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileHashUtil {

	public String calculateHash(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[8192];
			int read;
			while ((read = fis.read(buffer)) != -1) {
				digest.update(buffer, 0, read);
			}
			byte[] hash = digest.digest();
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			log.error("파일 해시 계산 중 오류 발생", e);
			throw new DocumentProcessException("해시 계산 실패", e);
		}
	}
}
