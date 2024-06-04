package ksmart.ks50team01.platform.ranking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;

@Mapper
public interface UserRankingMapper {
	// 회원추천리스트 삭제
	void removeUserRanking(String userRankingId);
	
	// 회원추천리스트 수정정보 가져오기
	UserRanking getUserRankingInfoById(String userRankingId);
	
	// 회원추천리스트 수정
	int modifyUserRanking(UserRanking userRanking);
	
	// 회원추천리스트 등록
	int addUserRanking(UserRanking userRanking);
	
	// 회원추천리스트 중복체크
	boolean userRankingListCheck(int userRank);
	
	// 회원추천리스트 조회
	List<UserRanking> getUserRankingList();
}
