package ksmart.ks50team01.user.board.service;

import java.util.List;

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
	
	
	
	public List<UCategory> getPostCateList(){
		List<UCategory> postCateList = uCommunityMapper.getPostCateList();
		
		log.info("게시글 카테고리 조회 결과: {}", postCateList);
		return postCateList;
	}
	
	
	
	
    /**
     * 게시글 상세 조회
     * @param postNum 조회할 게시글 ID
     * @return UCommunity
     */
    public UCommunity getPostByPostNum(String postNum) {
    	UCommunity postDetail = uCommunityMapper.getPostByPostNum(postNum);
    	log.info("getPostByPostNum : {}", postDetail);
        return postDetail;
    }
    
    
    
	// 조회수 증가
    public int increaseViewCount(String postNum) {
        return uCommunityMapper.increaseViewCount(postNum);
    }
    
    
    // 게시글 번호에 해당하는 모든 댓글을 가져오는 메서드 호출
    public List<UComment> getCommentByPostNum(String postNum) {
        return uCommunityMapper.getCommentByPostNum(postNum); 
    }
    
    // 특정 게시물의 총 댓글 수
	public int getCommentCntByPostNum(String postNum) {
	    int commentCnt = uCommunityMapper.getCommentCntByPostNum(postNum);
	    return commentCnt;
	}

	
	
	
	/**
	 * 게시글 작성
	 * @param uCommunity
	 */
    public void insertPost(UCommunity uCommunity) {
        
        // 게시글 DB에 저장
        uCommunityMapper.insertPost(uCommunity);
    }

	


	/**
	 * 게시글 수정
	 * @param uCommunity
	 */
	public void postModify(UCommunity uCommunity) {
		uCommunityMapper.postModify(uCommunity);
	}


	/**
	 * 주어진 번호에 해당하는 게시글 정보 조회
	 * @param postNum 조회할 게시글 번호
	 * @return 조회된 uCommunity 객체, 없을 경우 null
	 */
	public UCommunity getPostInfoByNum(String postNum) {
		return uCommunityMapper.getPostInfoByNum(postNum);
	}


	/**
	 * 게시글 삭제
	 */
	public void postDelete(String postNum) {
		uCommunityMapper.postDelete(postNum);
		
	}


	/**
	 * 파일목록
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



	public List<UComment> getPostCommentList(String postNum) {
		return uCommunityMapper.getPostCommentList(postNum);
	}



	public List<UCommunity> getSearchList(String searchKey, String searchValue) {
		// 검색항목
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


}
	