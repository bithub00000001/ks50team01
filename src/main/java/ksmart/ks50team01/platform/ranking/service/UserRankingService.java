package ksmart.ks50team01.platform.ranking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.ranking.dto.UserRanking;
import ksmart.ks50team01.platform.ranking.mapper.UserRankingMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRankingService {
	
	private final UserRankingMapper userRankingMapper;
	
	public List<UserRanking> getUserRankingList(){
		List<UserRanking> userRankingList = userRankingMapper.getUserRankingList();
		
		return userRankingList;
	}
}
