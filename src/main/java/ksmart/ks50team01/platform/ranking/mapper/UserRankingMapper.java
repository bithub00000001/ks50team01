package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;

@Mapper
public interface UserRankingMapper {
	
	void removeUserRanking(String userRankingId);
	
	UserRanking getUserRankingInfoById(String userRankingId);
	
	int modifyUserRanking(UserRanking userRanking);
		
	int addUserRanking(UserRanking userRanking);
	
	boolean userRankingListCheck(String userRankingId);
	
	List<UserRanking> getUserRankingList();
}
