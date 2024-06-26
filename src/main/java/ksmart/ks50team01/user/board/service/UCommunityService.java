package ksmart.ks50team01.user.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UComment;
import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.dto.UPostFile;
import ksmart.ks50team01.user.board.mapper.UCommunityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UCommunityService {

	private final UCommunityMapper uCommunityMapper;
	
	/**
	 * 게시글 조회
	 * @return List<UCommunity>
	 */
	public List<UCommunity> getPostList(){
		List<UCommunity> postList = uCommunityMapper.getPostList();
		
		log.info("게시글 조회 결과: {}", postList);
		return postList;
	}
	
	
	// 게시글 카테고리 조회
	public List<UCategory> getPostCateList(){
		List<UCategory> postCateList = uCommunityMapper.getPostCateList();
		
		log.info("게시글 카테고리 조회 결과: {}", postCateList);
		return postCateList;
	}
	
	
	// 검색목록 조회
	public List<UCommunity> getSearchList(String searchKey, String searchValue) {
		if("postTitle".equals(searchKey)) {
			searchKey = "P.PST_TTL";
		}else if("postRegId".equals(searchKey)) {
			searchKey = "P.REG_MBR_ID";;
		}else {
			searchKey = "";
		}
		
		List<UCommunity> postList = uCommunityMapper.getSearchList(searchKey, searchValue);
		
		return postList;
	}
	
	
	
    /**
     * 게시글 상세 조회
     * @param postNum 조회할 게시글 ID
     * @return UCommunity
     */
    public UCommunity getPostDetail(String postNum) {
    	UCommunity postDetail = uCommunityMapper.getPostDetail(postNum);
    	log.info("getPostByPostNum : {}", postDetail);
        return postDetail;
    }
    

	/**
	 * 해당 번호의 게시글 조회
	 * @param postNum 조회할 게시글 번호
	 * @return 조회된 uCommunity 객체, 없을 경우 null
	 */
	public UCommunity getPostInfoByNum(String postNum) {
		return uCommunityMapper.getPostInfoByNum(postNum);
	}
    
	
	// 해당 게시글의 모든 댓글을 가져오는 메서드
	public List<UComment> getCommentByPostNum(String postNum) {
		return uCommunityMapper.getCommentByPostNum(postNum); 
	}
	
	// 해당 게시글의 총 댓글 수
	public int getCommentCntByPostNum(String postNum) {
		int commentCnt = uCommunityMapper.getCommentCntByPostNum(postNum);
		return commentCnt;
	}
	
	
    
	// 조회수 증가
    public int increaseViewCount(String postNum) {
        return uCommunityMapper.increaseViewCount(postNum);
    }
    
	
	/**
	 * 게시글 작성
	 * @param uCommunity
	 */
    public void postAdd(UCommunity uCommunity) {
        
        uCommunityMapper.postAdd(uCommunity);
    }



	/**
	 * 게시글 수정
	 * @param uCommunity
	 */
	public void postModify(UCommunity uCommunity) {
		uCommunityMapper.postModify(uCommunity);
	}


	/**
	 * 게시글 삭제
	 */
	public void postRemove(String postNum) {

		uCommunityMapper.postRemove(postNum);
		
	}
	
	
	/**
	 * 게시글 삭제 시 해당 게시글의 댓글 삭제
	 */
	public void postCommentRemove(String postNum) {
		
		uCommunityMapper.postCommentRemove(postNum);
		
	}




	/**
	 * 파일목록 조회
	 * @return
	 */
	public List<UPostFile> getFileList() {
		List<UPostFile> postFileList = uCommunityMapper.getFileList();
		return postFileList;
	}


	// 댓글 작성
	public void commentSave(String commentRegId, String postNum, String commentContent) {
		UComment uComment = new UComment();
		uComment.setPostNum(postNum);
		uComment.setCommentRegId(commentRegId);
		uComment.setCommentContent(commentContent);
		uCommunityMapper.commentSave(uComment);
	}

	
	public UComment getCommentById(String commentNum) {
		return uCommunityMapper.getCommentById(commentNum);
	}
	
	
	// 댓글 삭제
	public void commentRemove(String commentNum) {
		uCommunityMapper.commentRemove(commentNum);
		
	}
	
	
	
	// 답글 작성
	public void replySave(String commentRegId, String postNum, String commentContent, String parentCommentId) {
		UComment uComment = new UComment();
		uComment.setPostNum(postNum);
		uComment.setCommentRegId(commentRegId);
		uComment.setCommentContent(commentContent);
		uCommunityMapper.replySave(uComment);
	}

	
	// 게시글 댓글 조회
	public List<UComment> getPostCommentList(String postNum) {
		return uCommunityMapper.getPostCommentList(postNum);
	}


	// 좋아요 수 증가
	public void increaseLikeCount(String postNum) {
		uCommunityMapper.increaseLikeCount(postNum);
		
	}


	public UCommunity getPostByNum(String postNum) {
		return uCommunityMapper.getPostByNum(postNum);
	}

	// 싫어요 수 증가
	public void increaseDislikeCount(String postNum) {
		uCommunityMapper.increaseDislikeCount(postNum);
	}

	
	// 댓글 수정
	public void commentModify(UComment uComment) {
		uCommunityMapper.commentModify(uComment);
	}


	public Map<String, Object> getPostList(int currentPage) {
	    int rowPerPage = 15;

	    int startRow = (currentPage - 1) * rowPerPage;

	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("startRow", startRow);
	    paramMap.put("rowPerPage", rowPerPage);

	    List<Map<String, Object>> postList = uCommunityMapper.getPostListByPage(paramMap);
	    int rowCnt = uCommunityMapper.getPostListRowCnt();
	    
	    // 나눗셈 결과를 올바르게 처리하기 위해 double 형으로 형변환 후 올림
	    int lastPage = (int)Math.ceil((double)rowCnt / rowPerPage); 

	    int startPageNum = 1;
	    int lastPageNum = 5;

	    // 마지막 페이지가 5보다 작으면 lastPage로 설정
	    lastPageNum = Math.min(lastPage, lastPageNum); 

	    if (currentPage > 6 && lastPage > 9) {
	        startPageNum = currentPage - 5;
	        lastPageNum = currentPage + 4;
	        if (lastPageNum >= lastPage) {
	            startPageNum = lastPage - 9;
	            lastPageNum = lastPage;
	        }
	    }

	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("postList", postList);
	    resultMap.put("lastPage", lastPage);
	    resultMap.put("startPageNum", startPageNum);
	    resultMap.put("lastPageNum", lastPageNum);

	    return resultMap;
	}


}
	