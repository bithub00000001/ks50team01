package ksmart.ks50team01.platform.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ksmart.ks50team01.platform.board.dto.PCommunity;

@Mapper
public interface PCommunityMapper {
	
	// 커뮤니티 게시글 목록 조회
	List<PCommunity> getCommunityList();
	
	// 게시글 목록 조회
	List<PCommunity> getPostList();
	
	// 댓글 조회
	List<PCommunity> getCommentList();

	// 게시글 번호로 게시글 조회
	PCommunity getPostByNum(String postNum);

	// 게시글 비활성화
	void deactivatePost(@Param("postNum") String postNum);

	// 카테고리별 게시글 목록 조회
	List<PCommunity> getCommunityListByCategory(String category); 
	
}
