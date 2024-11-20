package ksmart.ks50team01.documentation.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 문서 트리 응답 DTO
 */
@Data
@Builder
public class DocumentTreeResponse {
	private String id;          // 노드 고유 ID
	private String text;        // 표시될 이름
	private String type;        // folder 또는 file
	private String path;        // 파일 경로
	private String downloadUrl; // 다운로드 URL
	private List<DocumentTreeResponse> children; // 하위 항목
}
