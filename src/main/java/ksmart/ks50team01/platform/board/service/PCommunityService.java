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
	public void disablePosts(List<String> postNumList) {
	    for (String postNum : postNumList) {
	        PCommunity post = pCommunityMapper.getPostByNum(postNum); // 게시물 번호를 기반으로 게시물 가져오기
	        if (post != null) {
	            post.setPostAct("N"); // postAct 값을 "N"으로 설정하여 비활성화
	            pCommunityMapper.updatePost(post); // 변경된 내용을 데이터베이스에 반영
	        }
	    }
	}
	
	// 게시뮬 비활성화 
	public void deactivatePost(PCommunity pcommunity) {
		pCommunityMapper.deactivatePost(pcommunity);
		
	}

	
	public void deactivatePost(String postNum) {
		pCommunityMapper.deactivatePost(postNum);
		
	}
	
    public void deactivatePost(List<String> postNums) {
        for (String postNum : postNums) {
            pCommunityMapper.deactivatePost(postNum);
        }
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







	
	

}
