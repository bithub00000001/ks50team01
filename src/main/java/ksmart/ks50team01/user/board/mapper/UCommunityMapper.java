package ksmart.ks50team01.user.board.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import ksmart.ks50team01.user.board.dto.UCommunity;


@Mapper
public interface UCommunityMapper {
	
	// 게시글 조회
	List<UCommunity> getPostList();
	
	// 특정 게시글 조회
	UCommunity getPostByPostNum(String postNum);
	
	//게시글 카테고리 조회
	List<String> getPostCateList();
	
	
	// 댓글 조회
	List<UCommunity> getCommentByPostNum(String postNum);
	
	// 게시글 저장
	String postSave(String postCategory, String postTitle, String postContent, MultipartFile postFile);
	
	// 게시글 수정
	void postUpdate(String postCategory, String postTitle, String postContent, MultipartFile postFile);

	// 게시글 삭제
	void deletePost(String postNum);

	// 게시글 DB에 추가
	void insertPost(String postCategory, String postTitle, String postContent, MultipartFile postFile);

	// 게시글 조회수 증가
	void increaseViewCount(String postNum);
	
	// 답글 저장
	String replySave(String replyContent);

	
	// 특정 게시물의 댓글 수를 가져오는 메서드 추가
	//Integer getCommentCntByPostNum(String postNum);
	
	
	/**
	public Integer getCommentCntByPostNum(String postNum) {
	    Integer count = uCommunityMapper.getCommentCntByPostNum(postNum);
	    return count != null ? count : 0;
	} */
	


	
	/**
	// 게시글 작성
	void postSave(@Param("postCategory") String postCategory, 
				  @Param("postTitle") String postTitle, 
				  @Param("postContent") String postContent, 
				  @Param("postFile") String postFile);
				  */

}
