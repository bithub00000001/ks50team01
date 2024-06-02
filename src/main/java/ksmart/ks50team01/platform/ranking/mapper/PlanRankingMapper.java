package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.PlanRanking;



@Mapper
public interface PlanRankingMapper {
	
	// 여행계획리스트 삭제
	void removePlanRanking(String userTripPlanId);
	
	// 여행계획리스트 수정정보 가져오기
	PlanRanking getPlanRankingInfoById(String userTripPlanId);
	
	// 여행계획리스트 수정
	int modifyPlanRanking(PlanRanking planRanking);
	
	// 여행계획리스트 등록
	int addPlanUserRanking(PlanRanking planRanking);
	
	// 여행계획리스트 중복체크
	boolean planRankingListCheck(int tripPlanRank);
	
	// 여행계획리스트 조회
	List<PlanRanking> getPlanRankingList();
}
