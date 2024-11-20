package ksmart.ks50team01.documentation.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 문서 처리 결과 DTO
 */
@Data
@Builder
public class DocumentProcessResult {
	private String fileName;
	private String fileUrl;
	private String fileHash;
	private boolean isNewDocument;  // 해쉬 비교를 위해 추가
}
