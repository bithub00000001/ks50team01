package ksmart.ks50team01.documentation.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 문서 이력 엔티티
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {
	private Long id;
	private String fileName;
	private String fileUrl;
	private String fileHash;
	private DocumentStatus status;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	/**
	 * 문서 상태 열거형
	 */
	public enum DocumentStatus {
		NEW, PROCESSING, COMPLETED, ERROR
	}
}
