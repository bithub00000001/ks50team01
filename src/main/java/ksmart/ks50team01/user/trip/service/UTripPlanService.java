package ksmart.ks50team01.user.trip.service;

import ksmart.ks50team01.user.trip.dto.UTripOption;

public interface UTripPlanService {
	// 데이트 레인지를 받아 출발 날짜와 도착 날짜로 분리하는 메서드
	UTripOption parseDateRange(UTripOption uTripOption);

	// 데이트 레인지가 존재하면 출발 날짜와 도착 날짜 사이의 일수를 계산
	UTripOption calculateNumDate(UTripOption uTripOption);
}
