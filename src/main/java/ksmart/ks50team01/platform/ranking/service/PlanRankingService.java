package ksmart.ks50team01.platform.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.ranking.dto.PlanRanking;
import ksmart.ks50team01.platform.ranking.mapper.PlanRankingMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanRankingService {

	private final PlanRankingMapper planRankingMapper;
	
	public void removePlanRanking(String userTripPlanId) {
		planRankingMapper.removePlanRanking(userTripPlanId);
	}
	
	public int modifyPlanRanking(PlanRanking planRanking) {
		return planRankingMapper.modifyPlanRanking(planRanking);
	}
	
	public PlanRanking getPlanRankingInfoById(String userTripPlanId) {
		PlanRanking planRanking = planRankingMapper.getPlanRankingInfoById(userTripPlanId);
		return planRanking;
	}
	
	public int addPlanUserRanking(PlanRanking planRanking) {
		return planRankingMapper.addPlanUserRanking(planRanking);
	}
	
	public boolean planRankingListCheck(String userTripPlanId) {
		return planRankingMapper.planRankingListCheck(userTripPlanId);
	}
	
	public List<PlanRanking> getPlanRankingList(){
		
	List<PlanRanking> planRankingList = planRankingMapper.getPlanRankingList();
		
		return planRankingList;
	}
	
	
}
