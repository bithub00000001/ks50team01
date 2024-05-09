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
	
	private PCommunityMapper pCommunityMapper;
	
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
