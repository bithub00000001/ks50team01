package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.dto.RankingApi;


@Mapper
public interface RankingMapper {
	// 관계리스트 삭제
	void removeRankingInfo(String pfRankInfoId);
	
	// 플랫폼 추천리스트 삭제
	void removeRanking(String pRankingId);
	
	// 플랫폼 추천리스트 수정
	int modifyRanking(Ranking ranking);
	
	// 플랫폼 추천리스트 수정정보 가져오기
	Ranking getRankingInfoById(String pRankingId);
	
	// 관계리스트 수정
	int modifyRankingInfo(RankingApi rankingApi);
	
	// 관계리스트 수정정보 가져오기
	RankingApi getModifyRankingInfo(String pfRankInfoId);
	
	// 플랫폼 추천리스트 중복체크
	boolean rankingListCheck(int artclRankigNum);
	
	// 관계리스트 중복체크
	boolean rankingApiListCheck(String pfRankInfoId);
	
	// api 컨텐츠 아이디 가져오기
	RankingApi getDestinationContentId(String destinationCId);
	
	// 관계리스트 등록
	int addApiRanking(RankingApi rankingApi);
	
	// 관계리스트 조회
	List<RankingApi> getRankingInfoList();
	
	// 플랫폼 추천리스트 등록
	int addRanking(Ranking ranking);
	
	// 플랫폼 추천리스트 조회
	List<Ranking> getRankingList();
}
