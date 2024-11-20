package ksmart.ks50team01.documentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 문서 업데이트 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentUpdateRequest {
	private String apiKey;
	private String targetUrl;
	private Boolean forceUpdate;

	// 요청 유효성 검증
	public boolean isValid() {
		return targetUrl != null && !targetUrl.isEmpty();
	}
}
