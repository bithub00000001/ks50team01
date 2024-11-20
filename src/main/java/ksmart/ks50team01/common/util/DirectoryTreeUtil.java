package ksmart.ks50team01.common.util;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ksmart.ks50team01.documentation.dto.response.DocumentTreeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 디렉토리 트리 생성을 위한 유틸리티 클래스
 */
@Component
@Slf4j
public class DirectoryTreeUtil {

	@Value("${document.storage.path}")
	private String storagePath;  // 기본 저장 경로 추가

	/**
	 * 디렉토리 트리 생성
	 */
	public List<DocumentTreeResponse> createDirectoryTree(File directory) {
		List<DocumentTreeResponse> tree = new ArrayList<>();
		File[] files = listAndSortFiles(directory);

		if (files != null) {
			for (File file : files) {
				DocumentTreeResponse node = DocumentTreeResponse.builder()
					.id(generateNodeId(file))
					.text(file.getName())
					.type(file.isDirectory() ? "folder" : "file")
					.build();

				if (file.isDirectory()) {
					node.setChildren(createDirectoryTree(file));
				} else if (file.getName().toLowerCase().endsWith(".pdf")) {
					// storagePath를 기준으로 한 상대 경로 계산
					String relativePath = getRelativePath(new File(storagePath), file);
					String encodedPath = encodePathForUrl(relativePath);
					node.setDownloadUrl("/documentation/documents/download/" + encodedPath);
					log.debug("File path: {}, Download URL: {}", relativePath, node.getDownloadUrl());
				}
				tree.add(node);
			}
		}
		return tree;
	}


	/**
	 * URL에 사용할 수 있도록 경로 인코딩
	 */
	private String encodePathForUrl(String path) {
		try {
			// 경로 구분자를 URL 형식으로 변경하고 인코딩
			String normalizedPath = path.replace("\\", "/");

			// 경로 세그먼트별로 인코딩
			String[] segments = normalizedPath.split("/");
			StringBuilder encodedPath = new StringBuilder();

			for (int i = 0; i < segments.length; i++) {
				if (i > 0) {
					encodedPath.append("/");
				}
				// 공백과 특수문자를 적절히 인코딩
				encodedPath.append(URLEncoder.encode(segments[i], StandardCharsets.UTF_8)
					.replace("+", "%20"));  // 공백을 %20으로 변경
			}

			return encodedPath.toString();
		} catch (Exception e) {
			log.error("경로 인코딩 중 오류 발생: {}", path, e);
			return path;
		}
	}

	/**
	 * 파일 목록 조회 및 정렬
	 */
	private File[] listAndSortFiles(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			Arrays.sort(files, (f1, f2) -> {
				if (f1.isDirectory() && !f2.isDirectory()) return -1;
				if (!f1.isDirectory() && f2.isDirectory()) return 1;
				return f1.getName().compareTo(f2.getName());
			});
		}
		return files;
	}

	/**
	 * 상대 경로 계산
	 * storagePath를 기준으로 전체 상대 경로를 계산
	 */
	private String getRelativePath(File baseDir, File file) {
		String basePath = baseDir.getAbsolutePath();
		String filePath = file.getAbsolutePath();

		// baseDir로부터의 상대 경로 계산
		String relativePath = filePath.substring(basePath.length());
		if (relativePath.startsWith(File.separator)) {
			relativePath = relativePath.substring(1);
		}

		return relativePath;
	}


	/**
	 * 노드 ID 생성
	 */
	private String generateNodeId(File file) {
		return Base64.getEncoder()
			.encodeToString(file.getAbsolutePath().getBytes())
			.replaceAll("[^a-zA-Z0-9]", "");
	}
}
