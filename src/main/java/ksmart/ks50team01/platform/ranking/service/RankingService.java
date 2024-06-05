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
	
	/**
	 * 관계리스트 삭제
	 * @param pfRankInfoId
	 */
	public void removeRankingInfo(String pfRankInfoId) {
		rankingMapper.removeRankingInfo(pfRankInfoId);
	}
	/**
	 * 플랫폼 추천리스트 삭제
	 * @param pRankingId
	 */
	public void removeRanking(String pRankingId) {
		rankingMapper.removeRanking(pRankingId);
	}
	/**
	 * 플랫폼 추천리스트 수정
	 * @param ranking
	 * @return
	 */
	public int modifyRanking(Ranking ranking) {
		return rankingMapper.modifyRanking(ranking);
	}
	
	/**
	 * 플랫폼 추천리스트 수정정보 가져오기
	 * @param pRankingId
	 * @return
	 */
	public Ranking getRankingInfoById(String pRankingId) {
		Ranking ranking = rankingMapper.getRankingInfoById(pRankingId);
		return ranking;
	}
	/**
	 * 관계리스트 수정
	 * @param rankingApi
	 * @return
	 */
	public int modifyRankingInfo(RankingApi rankingApi) {
		return rankingMapper.modifyRankingInfo(rankingApi);
	}
	/**
	 * 관계리스트 수정정보 가져오기
	 * @param pfRankInfoId
	 * @return
	 */
	public RankingApi getModifyRankingInfo(String pfRankInfoId) {
		RankingApi rankingApi = rankingMapper.getModifyRankingInfo(pfRankInfoId);
		return rankingApi;
	}
	/**
	 * 플랫폼추천 리스트 중복체크
	 * @param pRankingNum
	 * @return
	 */
	public boolean rankingListCheck(int artclRankigNum) {
		return rankingMapper.rankingListCheck(artclRankigNum);
	}
	/**
	 * 관계리스트 중복체크
	 * @param pfRankInfoId
	 * @return
	 */
	public boolean rankingApiListCheck(String pfRankInfoId) {
		return rankingMapper.rankingApiListCheck(pfRankInfoId);
	}
	/**
	 * api 컨텐츠아이디 가져오기
	 * @param destinationCId
	 * @param destinationTitle
	 * @return
	 */
	public RankingApi getDestinationContentId(String destinationCId, String destinationTitle) {
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
	/**
	 * 관계리스트 등록
	 * @param rankingApi
	 * @return
	 */
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
	/**
	 * 관계리스트 조회
	 * @return rankingApiList
	 */
	public List<RankingApi> getRankingInfoList(){
		
		List<RankingApi> rankingApiList = rankingMapper.getRankingInfoList();
		
		return rankingApiList;
	}
}
