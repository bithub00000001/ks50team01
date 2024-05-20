package ksmart.ks50team01.platform.trip.service;

import java.util.List;

import ksmart.ks50team01.platform.trip.dto.PTourApi;
import ksmart.ks50team01.platform.trip.dto.PTripPlan;

public interface PTripPlanService {
	// 여행 계획 전체 목록 조회
	List<PTripPlan> getAllPTripPlan();

	// 여행 계획 id에 해당하는 계획 조회
	PTripPlan getPTripPlanById(String planId);

	// 여행 계획 수정
	int UpdatePTripPlan(PTripPlan pTripPlan);

	// 지역 코드 목록 조회
	List<PTourApi> getAreaCodeList();

	// 시군 코드 목록 조회
	List<PTourApi> getSigunguCodeList();

	// 지역 코드에 해당하는 시군 코드 조회
	List<PTourApi> getSigunguCodesByAreaCode(String areaCode);

	// 여행지 정보 목록 조회
	List<PTourApi> getDestinationList();

	// 컨텐츠 아이디와 일치하는 여행지 정보 목록 조회
	List<PTourApi> getDestinationListByContentType(String contentTypeId);

	// 여행지 세부 정보 조회
}
