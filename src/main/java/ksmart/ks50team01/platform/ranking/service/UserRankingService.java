package ksmart.ks50team01.platform.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;
import ksmart.ks50team01.platform.ranking.mapper.UserRankingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserRankingService {
	
	private final UserRankingMapper userRankingMapper;
	
	/**
	 * 회원추천리스트 삭제
	 * @param userRankingId
	 */
	public void removeUserRanking(String userRankingId) {
		userRankingMapper.removeUserRanking(userRankingId);
	}
	/**
	 * 회원추천리스트 수정
	 * @param userRanking
	 * @return
	 */
	public int modifyUserRanking(UserRanking userRanking) {
		return userRankingMapper.modifyUserRanking(userRanking);
	}
	/**
	 * 회원추천리스트 수정정보
	 * @param userRankingId
	 * @return
	 */
	public UserRanking getUserRankingInfoById(String userRankingId) {
		UserRanking userRanking = userRankingMapper.getUserRankingInfoById(userRankingId);
		return userRanking;
	}
	/**
	 * 회원추천리스트 등록
	 * @param userRanking
	 * @return
	 */
	public int addUserRanking(UserRanking userRanking) {
		return userRankingMapper.addUserRanking(userRanking);
	}
	/**
	 * 회원추천리스트 중복체크
	 * @param userRank
	 * @return
	 */
	public boolean userRankingListCheck(int userRank) {
		return userRankingMapper.userRankingListCheck(userRank);
	}
	/**
	 * 회원추천리스트 조회
	 * @return
	 */
	public List<UserRanking> getUserRankingList(){
		
		List<UserRanking> userRankingList = userRankingMapper.getUserRankingList();
		log.info("UserRankingService getUserRankingList: {}", userRankingList);
		
		return userRankingList;
	}
}
