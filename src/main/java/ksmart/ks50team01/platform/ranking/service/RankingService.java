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

	public Ranking getRankingInfoById(String pRankingNum) {
		
		return rankingMapper.getMemberInfoById(pRankingNum);
	}
	/**
	 * 플랫폼 추천 리스트 중복체크
	 * @param pRankingNum
	 * @return
	 */
	public boolean rankingListCheck(String pRankingNum) {
		return rankingMapper.rankingListCheck(pRankingNum);
	}
	
	/**
	 * 플랫폼 추천 리스트 등록
	 * @param ranking
	 */
	public int addRanking(Ranking ranking) {
		return rankingMapper.addRanking(ranking);
	}
	
	/**
	 * 플랫폼 추천 조회
	 * @return rankingList
	 */
	public List<Ranking> getRankingList(){
		
		List<Ranking> rankingList = rankingMapper.getRankingList();
		
		return rankingList;
	}
}