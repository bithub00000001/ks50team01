package ksmart.ks50team01.chimney.cleansys.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDoubleDeserializer extends JsonDeserializer<Double> {
	@Override
	public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String text = p.getText();

		try {
			// 정상적인 Double 값이면 변환
			return Double.parseDouble(text);
		} catch (NumberFormatException e) {
			// 숫자로 변환 불가능한 경우 null 반환 또는 기본값 설정
			return null; // 또는 0.0 등 기본값 설정 가능
		}
	}
}
