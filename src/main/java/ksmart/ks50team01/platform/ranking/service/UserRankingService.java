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
	
	public int modifyUserRanking(UserRanking userRanking) {
		return userRankingMapper.modifyUserRanking(userRanking);
	}
	
	public UserRanking getUserRankingInfoById(String userRankingId) {
		UserRanking userRanking = userRankingMapper.getUserRankingInfoById(userRankingId);
		return userRanking;
	}
	
	public int addUserRanking(UserRanking userRanking) {
		return userRankingMapper.addUserRanking(userRanking);
	}
	
	public boolean userRankingListCheck(String userRankingId) {
		return userRankingMapper.userRankingListCheck(userRankingId);
	}
	
	public List<UserRanking> getUserRankingList(){
		
		List<UserRanking> userRankingList = userRankingMapper.getUserRankingList();
		log.info("UserRankingService getUserRankingList: {}", userRankingList);
		
		return userRankingList;
	}
}
