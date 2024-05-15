package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.Ranking;

@Mapper
public interface RankingMapper {
	
	Ranking getRankingInfoById(String pRankingId);
	
	int modifyRanking(Ranking ranking);
	
	boolean rankingListCheck(String pRankingNum);
		
	int addRanking(Ranking ranking);
	
	List<Ranking> getRankingList();
}
