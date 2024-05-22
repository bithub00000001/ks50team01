package ksmart.ks50team01.user.trip.service;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;

import ksmart.ks50team01.user.trip.dto.UTripOption;

public interface UTripPlanService {
	// 데이트 레인지를 받아 출발 날짜와 도착 날짜로 분리하는 메서드
	UTripOption parseDateRange(UTripOption uTripOption);

	// 데이트 레인지가 존재하면 출발 날짜와 도착 날짜 사이의 일수를 계산
	UTripOption calculateNumDate(UTripOption uTripOption);

	// 출발 날짜와 도착 날짜를 입력 받아 몇박 몇일 혹은 미정을 반환
	UTripOption calculateTripDuration(UTripOption uTripOption);

	Map<String, Object> getTourInfoObject(String content) throws JsonProcessingException;

	//
}
