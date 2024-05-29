package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.Ranking;
import ksmart.ks50team01.platform.ranking.dto.RankingApi;


@Mapper
public interface RankingMapper {
	
	void removeRankingInfo(String pfRankInfoId);
	
	void removeRanking(String pRankingId);
	
	Ranking getRankingInfoById(String pRankingId);
	
	int modifyRanking(Ranking ranking);
	
	boolean rankingListCheck(String pRankingId);
	
	boolean rankingApiListCheck(String pfRankInfoId);
	
	RankingApi getDestinationContentId(String destinationCId);
	
	int addRanking(Ranking ranking);
	
	int addApiRanking(RankingApi rankingApi);
	
	List<RankingApi> getRankingInfoList();
	
	List<Ranking> getRankingList();
}
