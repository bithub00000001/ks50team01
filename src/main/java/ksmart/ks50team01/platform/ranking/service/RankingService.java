package ksmart.ks50team01.platform.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.mapper.RankingMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RankingService {

	private final RankingMapper rankingMapper;
	
	public List<Ranking> getRankingList(){
		
		List<Ranking> rankingList = rankingMapper.getRankingList();
		
		return rankingList;
	}
}
