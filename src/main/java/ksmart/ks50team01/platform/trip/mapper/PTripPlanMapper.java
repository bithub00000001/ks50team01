package ksmart.ks50team01.platform.trip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.trip.dto.PTripPlan;

@Mapper
public interface PTripPlanMapper {
	// 여행 계획 전체 조회 메서드
	List<PTripPlan> getPlanList();

	// 여행 계획 id에 해당하는 계획 조회 메서드
	PTripPlan getTripPlanById(String planId);

	// 여행 계획 수정
	int updateTripPlan(PTripPlan plan);

	//
	/*
	* 여행 계획 업데이트
1) 지역 코드, 시군코드 셀렉트/옵션으로 밸류 전달
2) 시작 날짜, 종료 날짜 유효성 검사
3) 일자 자동 계산
4) 예산 차액 자동 계산 - 완료
5) 계획 상태 셀렉트/옵션으로 밸류 전달 - 완료
6) 공유 유무 셀렉트/옵션으로 밸류 전달 - 완료
7) 세션 아이디 전달 - 완료
	* */
}
