package ksmart.ks50team01.documentation.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;

/**
 * 파일 다운로드 응답 DTO
 */
@Data
@Builder
public class DocumentFileResponse {
	private Resource resource;
	private String fileName;
	private String contentType;
	private long fileSize;
}
