package ksmart.ks50team01.platform.trip.service;

import java.util.List;

import ksmart.ks50team01.platform.trip.dto.PTripPlan;

public interface PTripPlanService {
	// 여행 계획 전체 목록 조회
	List<PTripPlan> getAllPTripPlan();

	// 여행 계획 id에 해당하는 계획 조회
	PTripPlan getPTripPlanById(String planId);

	// 여행 계획 수정
	int UpdatePTripPlan(PTripPlan pTripPlan);
}
