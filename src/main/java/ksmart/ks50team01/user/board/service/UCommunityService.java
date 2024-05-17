package ksmart.ks50team01.user.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	
	public List<String> getPostCateList(){
		List<String> postCateList = uCommunityMapper.getPostCateList();
		
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
    
    
    
    public List<UCommunity> getCommentByPostNum(String postNum) {
        return uCommunityMapper.getCommentByPostNum(postNum); // 게시글 번호에 해당하는 모든 댓글을 가져오는 메서드 호출
    }
	
	
	
	
	
	
	
	
	
	/** 외않되 ... 
    // 특정 게시물의 댓글 수를 가져오는 메서드 추가
    public Integer getCommentCntList(String postNum) {
        Integer count = uCommunityMapper.getCommentCntByPostNum(postNum);
        return count != null ? count : 0;
    }
     */
    


	
	


	
	
    /**
     * 게시글 작성
     * @param uCommunity
     */
	/*    public void postWrite(String postRegId, String postCateNum, String postTitle, String postContent, MultipartFile postFile) throws Exception {
		
		// 저장할 경로 지정
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/user/files";
		
		// 랜덤으로 파일이름 생성
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + postFile.getOriginalFilename();
		
		File saveFile = new File(projectPath, fileName);
		
		// 파일 저장
		postFile.transferTo(saveFile);
		
	}
	*/
    
    

    // 게시글 작성
    public void insertPost(String postRegId, String postCateNum, String postTitle, String postContent, MultipartFile postFile) throws Exception {
    	if (postFile != null && !postFile.isEmpty()) {
    		
    	String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/user/files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + postFile.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        postFile.transferTo(saveFile);

        // 게시글 객체 생성 및 설정
        UCommunity post = new UCommunity();
        post.setPostRegId(postRegId);
        post.setPostCateNum(postCateNum);
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setPostFile(fileName);

        // 게시글 DB에 저장
        uCommunityMapper.insertPost(post);
    } else {
    	uCommunityMapper.insertPost(postRegId, postCateNum, postTitle, postContent, null);
    }

	
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








	/** 
	// 각 게시물의 댓글 수를 가져오는 메서드 추가
	public List<Integer> getCommentCntList(List<UCommunity> postList) {
		List<Integer> commentCntList = new ArrayList<>();
		for (UCommunity post : postList) {
			int commentCnt = uCommunityMapper.getCommentCntByPostNum(post.getPostNum());
			commentCntList.add(commentCnt);
		}
		return commentCntList;
	} */
    


}
	