package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.platform.board.dto.PCommunity;
import ksmart.ks50team01.platform.board.dto.PNotice;

@Mapper
public interface PCommunityMapper {
	
	// 게시글 조회
	List<PCommunity> getPostList();
	
	// 댓글 조회
		List<PCommunity> getCommentList();
}
	

	

