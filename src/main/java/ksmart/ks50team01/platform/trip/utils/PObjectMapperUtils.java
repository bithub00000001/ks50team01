package ksmart.ks50team01.platform.trip.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PObjectMapperUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
