package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.dto.UCommunity;


@Mapper
public interface UCommunityMapper {
	
	// 게시글 조회
	List<UCommunity> getPostList();
	
	// 특정 게시글 조회
	UCommunity getPostByPostNum(String postNum);
	
	// 댓글 조회
	List<UCommunity> getCommentByPostNum(String postNum);
	
	// 게시글 작성
	String postSave(UCommunity uCommunity);
	
	// 게시글 수정
	void postUpdate(UCommunity uCommunity);

	// 게시글 삭제
	void deletePost(String postNum);

	// 게시글 DB에 추가
	void insertPost(UCommunity uCommunity);

	

	

}
