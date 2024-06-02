package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCommunity;
import ksmart.ks50team01.platform.board.mapper.PCommunityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PCommunityService {
	
	private final PCommunityMapper pCommunityMapper;
	
	/**
	 * 게시물 비활성화
	 * @param postNum 비활성화할 게시물 번호
	 */
	public void deactivatePost(String postNum) {
		pCommunityMapper.deactivatePost(postNum);
		
	}

	
	
	/**
	 * 커뮤니티 조회
	 * @return List<PCommunity>
	 */
	public List<PCommunity> getCommunityList(){
		return pCommunityMapper.getCommunityList();
	}
	
	
	
	/**
	 * 게시글 조회
	 * @return List<PCommunity>
	 */
	public List<PCommunity> getPostList(){
		List<PCommunity> postList = pCommunityMapper.getPostList();
		log.info("게시글 조회 결과: {}", postList);
		return postList;
	}
	
	
	/**
	 * 댓글 조회
	 * @return List<PCommunity>
	 */
	public List<PCommunity> getCommentList(){
		return pCommunityMapper.getCommentList();
	}


	/**
	 * 해당 카테고리의 게시글 목록 조회
	 * @param category
	 * @return 카테고리에 해당하는 게시글 목록
	 */
	public List<PCommunity> getCommunityListByCategory(String category) {
		List<PCommunity> communityList = pCommunityMapper.getCommunityListByCategory(category);
		return communityList;
	}







	
	

}
