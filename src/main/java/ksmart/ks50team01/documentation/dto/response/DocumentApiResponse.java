package ksmart.ks50team01.documentation.dto.response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 포함한 생성자 추가
public class DocumentApiResponse {
	private String status;
	private String message;
	private LocalDateTime timestamp;
	private Map<String, Object> data;

	// 에러 메시지만 받는 생성자
	public DocumentApiResponse(String message) {
		this.status = "error";
		this.message = message;
		this.timestamp = LocalDateTime.now();
		this.data = new HashMap<>();
	}

	// 성공 응답을 위한 정적 팩토리 메소드
	public static DocumentApiResponse success(String message) {
		return DocumentApiResponse.builder()
			.status("success")
			.message(message)
			.timestamp(LocalDateTime.now())
			.data(new HashMap<>())
			.build();
	}

	// 데이터를 포함한 성공 응답을 위한 정적 팩토리 메소드
	public static DocumentApiResponse success(String message, Map<String, Object> data) {
		return DocumentApiResponse.builder()
			.status("success")
			.message(message)
			.timestamp(LocalDateTime.now())
			.data(data)
			.build();
	}

	// 에러 응답을 위한 정적 팩토리 메소드
	public static DocumentApiResponse error(String message) {
		return DocumentApiResponse.builder()
			.status("error")
			.message(message)
			.timestamp(LocalDateTime.now())
			.data(new HashMap<>())
			.build();
	}
}
