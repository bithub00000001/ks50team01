package ksmart.ks50team01.platform.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.dto.RankingApi;
import ksmart.ks50team01.platform.ranking.mapper.RankingMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RankingService {

	private final RankingMapper rankingMapper;
	
	public void removeRanking(String pRankingId) {
		rankingMapper.removeRanking(pRankingId);
	}
	
	public int modifyRanking(Ranking ranking) {
		return rankingMapper.modifyRanking(ranking);
	}
	
	
	public Ranking getRankingInfoById(String pRankingId) {
		Ranking ranking = rankingMapper.getRankingInfoById(pRankingId);
		return ranking;
	}
	/**
	 * 플랫폼 추천 리스트 중복체크
	 * @param pRankingNum
	 * @return
	 */
	public boolean rankingListCheck(String pRankingNum) {
		return rankingMapper.rankingListCheck(pRankingNum);
	}
	
	public boolean rankingApiListCheck(String pfRankInfoId) {
		return rankingMapper.rankingApiListCheck(pfRankInfoId);
	}
	
	public RankingApi getDestinationContentId(String destinationCId) {
		RankingApi rankingApi = rankingMapper.getDestinationContentId(destinationCId);
		return rankingApi;
	}
	/**
	 * 플랫폼 추천 리스트 등록
	 * @param ranking
	 */
	public int addRanking(Ranking ranking) {
		return rankingMapper.addRanking(ranking);
	}
	
	public int addApiRanking(RankingApi rankingApi) {
		return rankingMapper.addApiRanking(rankingApi);
	}
	
	/**
	 * 플랫폼 추천 조회
	 * @return rankingList
	 */
	public List<Ranking> getRankingList(){
		
		List<Ranking> rankingList = rankingMapper.getRankingList();
		
		return rankingList;
	}
	public List<RankingApi> getRankingInfoList(){
		
		List<RankingApi> rankingApiList = rankingMapper.getRankingInfoList();
		
		return rankingApiList;
	}
}
