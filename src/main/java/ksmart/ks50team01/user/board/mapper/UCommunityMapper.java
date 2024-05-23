package ksmart.ks50team01.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.dto.UPostFile;


@Mapper
public interface UCommunityMapper {
	
	// 게시글 조회
	List<UCommunity> getPostList();
	
	// 게시글 상세 조회
	UCommunity getPostByPostNum(String postNum);
	

	// 게시글 조회수 증가
	public int increaseViewCount(String postNum);
	
	// 게시글 번호로 해당 게시글의 댓글 수를 조회
	public int getCommentCntByPostNum(String postNum);
	

	
	
	//게시글 카테고리 조회
	List<UCategory> getPostCateList();
	
	
	// 댓글 조회
	List<UComment> getCommentByPostNum(String postNum);
	
	// 게시글 수정
	void postUpdate(String postCategory, String postTitle, String postContent, MultipartFile postFile);

	// 게시글 삭제
	void deletePost(String postNum);



	
	// 답글 저장
	String replySave(String replyContent);

	// 게시글 DB에 저장
	void insertPost(UCommunity post);

	// 게시글 수정 
	void postModify(UCommunity uCommunity);

	// 주어진 번호에 해당하는 게시글 정보 조회
	UCommunity getPostInfoByNum(String postNum);

	// 게시글 삭제
	void postDelete(String postNum);

	// 게시글 파일 리스트
	List<UPostFile> getFileList();





	




	
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
