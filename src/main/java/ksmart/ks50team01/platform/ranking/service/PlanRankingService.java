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
	
	/**
	 * 여행계획리스트 삭제
	 * @param userTripPlanId
	 */
	public void removePlanRanking(String userTripPlanId) {
		planRankingMapper.removePlanRanking(userTripPlanId);
	}
	/**
	 * 여행계획리스트 수정
	 * @param planRanking
	 * @return
	 */
	public int modifyPlanRanking(PlanRanking planRanking) {
		return planRankingMapper.modifyPlanRanking(planRanking);
	}
	/**
	 * 여행계획리스트 수정정보
	 * @param userTripPlanId
	 * @return
	 */
	public PlanRanking getPlanRankingInfoById(String userTripPlanId) {
		PlanRanking planRanking = planRankingMapper.getPlanRankingInfoById(userTripPlanId);
		return planRanking;
	}
	/**
	 * 여행계획리스트 등록
	 * @param planRanking
	 * @return
	 */
	public int addPlanUserRanking(PlanRanking planRanking) {
		return planRankingMapper.addPlanUserRanking(planRanking);
	}
	/**
	 * 여행계획리스트 중복체크
	 * @param userTripPlanId
	 * @return
	 */
	public boolean planRankingListCheck(int tripPlanRank) {
		return planRankingMapper.planRankingListCheck(tripPlanRank);
	}
	/**
	 * 여행계획리스트 조회
	 * @return
	 */
	public List<PlanRanking> getPlanRankingList(){
		
	List<PlanRanking> planRankingList = planRankingMapper.getPlanRankingList();
		
		return planRankingList;
	}
}
