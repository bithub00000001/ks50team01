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
	
	// 게시글 카테고리 조회
	List<UCategory> getPostCateList();
	
	// 검색목록 조회
	List<UCommunity> getSearchList(String searchKey, String searchValue);
	
	// 게시글 상세 조회
	UCommunity getPostDetail(String postNum);
	
	// 게시글 조회수 증가
	public int increaseViewCount(String postNum);
	
	// 게시글 번호로 게시글 댓글 수 조회
	public int getCommentCntByPostNum(String postNum);
	
	// 게시글 번호로 게시글 정보 조회
	UCommunity getPostInfoByNum(String postNum);
	
	// 게시글 번호로 게시글 댓글 조회
	List<UComment> getCommentByPostNum(String postNum);
	
	List<UComment> getPostCommentList(String postNum);
	
	// 게시글 작성
	void postAdd(UCommunity uCommunity);

	// 게시글 수정 
	void postModify(UCommunity uCommunity);

	// 게시글 삭제
	void postRemove(String postNum);

	// 게시글 파일 리스트 조회
	List<UPostFile> getFileList();

	// 댓글 작성
	void commentSave(UComment uComment);
	
	// 답글 저장
	String replySave(String replyContent);

}
