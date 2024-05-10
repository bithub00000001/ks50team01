package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;

@Mapper
public interface UserRankingMapper {
	
	List<UserRanking> getUserRankingList();
}
