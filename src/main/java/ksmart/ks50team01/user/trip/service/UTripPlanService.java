package ksmart.ks50team01.user.trip.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.trip.dto.UDayInfo;
import ksmart.ks50team01.user.trip.dto.UTripOption;

public interface UTripPlanService {
	// 데이트 레인지를 받아 출발 날짜와 도착 날짜로 분리하는 메서드
	UTripOption parseDateRange(UTripOption uTripOption);

	// 데이트 레인지가 존재하면 출발 날짜와 도착 날짜 사이의 일수를 계산
	UTripOption calculateNumDate(UTripOption uTripOption);

	// 출발 날짜와 도착 날짜를 입력 받아 몇박 몇일 혹은 미정을 반환
	UTripOption calculateTripDuration(UTripOption uTripOption);

	// 카카오 맵 API에 여행 정보 혹은 여행 상세 정보 객체를 반환하는 메서드
	Map<String, Object> getTourInfoObject(String content) throws JsonProcessingException;

	// 회원 중 일반 회원 목록을 조회하는 메서드
	List<Login> getUserMembers();

	// 여행 계획 작성 모달에서 회원 닉네임 검색 메서드
	List<Login> searchUserMembers(String nickname);

	// 여행 세부 계획 작성 페이지에서 정보를 받아 거리와 소요 시간을 계산하는 메서드
	Map<String, Object> calculateDistanceDuration(List<UDayInfo> locations) throws JsonProcessingException;

	// 임시 저장으로 여행 계획을 작성하는 메서드
	int addTempPlanInfo(UTripOption uTripOption);

	// 여행 계획 저장 혹은 업데이트 메서드
	void saveOrUpdateTempPlanInfo(UTripOption uTripOption);

	// 임시 저장된 여행 계획 목록 조회
	List<UTripOption> getTempPlanList(String sessionId);
}
