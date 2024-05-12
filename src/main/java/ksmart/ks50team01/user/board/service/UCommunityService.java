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
	


	
	/**
	 * 게시글 작성
	 * @param uCommunity
	 */
	public void postSave(UCommunity uCommunity, MultipartFile file) throws Exception {
		
		// 저장할 경로 지정
		String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
		
		// 랜덤으로 파일이름 생성
		UUID uuid = UUID.randomUUID();
		
		String fileName = uuid + "_" + file.getOriginalFilename();
		
		File saveFile = new File(projectPath, fileName);
		
		file.transferTo(saveFile);
		
		uCommunityMapper.postSave(uCommunity);
		uCommunityMapper.insertPost(uCommunity);
		
	}
	
	



    // 게시글 추가
    private void insertPost(UCommunity uCommunity) {
        uCommunityMapper.insertPost(uCommunity);
    }

	
	
	
	

    /**
     * 특정 게시글 상세 조회
     * @param postId 조회할 게시글 ID
     * @return UCommunity
     */
    public UCommunity getPostById(String postNum) {
        return uCommunityMapper.getPostById(postNum);
    }
    
    
    /**
     * 게시글 수정
     * @param uCommunity 수정된 게시글 정보
     */
    public void postUpdate(UCommunity uCommunity) {
        uCommunityMapper.postUpdate(uCommunity);
    }
    
    
    

    /**
     * 게시글 삭제
     * @param postId 삭제할 게시글 ID
     */
    public void deletePost(String postId) {
        uCommunityMapper.deletePost(postId);
    }
}
	