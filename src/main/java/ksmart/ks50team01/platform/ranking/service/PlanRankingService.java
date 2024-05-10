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
	
	public List<PlanRanking> getPlanRankingList(){
		
	List<PlanRanking> planRankingList = planRankingMapper.getPlanRankingList();
		
		return planRankingList;
	}
	
	
}
