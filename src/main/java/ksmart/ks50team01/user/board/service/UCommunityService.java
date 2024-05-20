package ksmart.ks50team01.user.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UCommunity;
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
    public List<UCommunity> getCommentByPostNum(String postNum) {
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
	/**
	 
    public void insertPost(String postRegId, String postCateNum, String postTitle, String postContent, MultipartFile postFile) throws Exception {
        String fileName = null;
        if (postFile != null && !postFile.isEmpty()) {
        	// 저장할 경로 지정
            String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/user/files";
            // 랜덤으로 파일이름 생성
            UUID uuid = UUID.randomUUID();
            fileName = uuid + "_" + postFile.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            postFile.transferTo(saveFile);
            log.info("File saved to: {}", saveFile.getAbsolutePath());
        }

        // 게시글 저장 로직
        UCommunity post = new UCommunity();
        post.setPostRegId(postRegId);
        post.setPostCateNum(postCateNum);
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setPostFile(fileName);

        // 게시글 DB에 저장
        uCommunityMapper.insertPost(post);
    } */

    
    
    
    // 게시글 작성
    public void insertPost(UCommunity uCommunity) {
        // 게시물을 데이터베이스에 삽입하기 전에 게시물 코드를 생성합니다.
        //String postCode = generatePostCode(uCommunityMapper.getPostNo());
        //uCommunityMapper.setPostCode(postCode); // 생성된 게시물 코드를 설정합니다.
        
        // 게시글 DB에 저장
        uCommunityMapper.insertPost(uCommunity);
    }

	
    // 가장 큰 PST_NO_NUMERIC 값을 조회하는 메서드
    public String getMaxPstNoNumeric() {
        return uCommunityMapper.getMaxPstNoNumeric();
    }
    

    // 게시물 코드 생성 메서드
    public String generatePostCode(int postNum) {
        // 게시물 번호를 기반으로 게시물 코드를 생성합니다.
        return "PST_" + String.format("%03d", postNum); // 예: PST_001, PST_002 등
    }

   
    
    

    
    
    
    /**
     * 게시글 수정
     * @param uCommunity 수정된 게시글 정보
     */
    public void postUpdate(String postCategory, String postTitle, String postContent, MultipartFile postFile) {
        uCommunityMapper.postUpdate(postCategory, postTitle, postContent, postFile);
    }
    
    
    

    /**
     * 게시글 삭제
     * @param postNum 삭제할 게시글 번호
     */
    public void deletePost(String postNum) {
        uCommunityMapper.deletePost(postNum);
    }


    
    







	public String replySave(String replyContent) {
		return uCommunityMapper.replySave(replyContent);
	}



	public void setPostCode(String postNum) {
		
	}









}
	