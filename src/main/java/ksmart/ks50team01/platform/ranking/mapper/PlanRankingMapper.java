package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.PlanRanking;



@Mapper
public interface PlanRankingMapper {
	
	void removePlanRanking(String userTripPlanId);

	PlanRanking getPlanRankingInfoById(String userTripPlanId);
	
	int modifyPlanRanking(PlanRanking planRanking);
	
	int addPlanUserRanking(PlanRanking planRanking);
	
	boolean planRankingListCheck(String userTripPlanId);
	
	List<PlanRanking> getPlanRankingList();
}
