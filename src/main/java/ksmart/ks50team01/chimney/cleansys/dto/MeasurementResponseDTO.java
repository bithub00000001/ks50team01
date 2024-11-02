package ksmart.ks50team01.chimney.cleansys.dto;

import java.util.List;

import lombok.Data;

/**
 * API 응답을 표현하는 최상위 DTO
 */
@Data
public class MeasurementResponseDTO {

	private Response response;

	@Data
	public static class Response {
		private Header header;
		private Body body;
	}

	@Data
	public static class Header {
		private String resultCode;
		private String resultMsg;
		private int totalCount;
	}

	@Data
	public static class Body {
		private List<MeasurementResultDTO> items;
	}
}
