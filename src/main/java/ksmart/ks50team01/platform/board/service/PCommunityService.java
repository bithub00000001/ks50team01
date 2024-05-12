package ksmart.ks50team01.platform.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart.ks50team01.platform.board.dto.PCommunity;
import ksmart.ks50team01.platform.board.mapper.PCommunityMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PCommunityService {
	
	private final PCommunityMapper pCommunityMapper;
	
	
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
		return pCommunityMapper.getPostList();
	}
	
	
	/**
	 * 댓글 조회
	 * @return List<PCommunity>
	 */
	public List<PCommunity> getCommentList(){
		return pCommunityMapper.getCommentList();
	}
	

}
