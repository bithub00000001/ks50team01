package ksmart.ks50team01.user.board.controller;

import java.util.Collections;
import java.util.List;


import org.springframework.data.relational.core.sql.FalseCondition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.service.UCommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value = "/community")
@RequiredArgsConstructor
@Slf4j
public class UCommunityController {
	
	private final UCommunityService uCommunityService;
	
	// 게시글 목록 조회
	@GetMapping({"/",""})
	public String postList(Model model) {
		List<UCommunity> postList = uCommunityService.getPostList();
		//List<Integer> commentList = uCommunityService.getCommentCntList(postList);
		model.addAttribute("postList", postList); // postList를 모델에 추가
		//model.addAttribute("commentList", commentList); // commentCntList를 모델에 추가
		model.addAttribute("title", "커뮤니티");
		return "user/board/postList";
		
	
	}
	
	
	// 게시글 리스트의 카테고리 리스트를 모델에 추가하는 어노테이션
	@ModelAttribute("postCateList")
	public List<String> postCateList() {
	    return uCommunityService.getPostCateList(); 
	}
	
	
	// 게시글 저장
	@PostMapping("/postSave")
	public String write(@RequestParam("postCategory") String postCategory,
	                    @RequestParam("postTitle") String postTitle,
	                    @RequestParam("postContent") String postContent,
	                    @RequestParam(value = "postFile", required = false) MultipartFile postFile,
	                    RedirectAttributes redirectAttributes) {
	    try {
	        // 입력된 제목과 내용을 UCommunity 객체에 설정
	        UCommunity uCommunity = new UCommunity();
	        uCommunity.setPostTitle(postTitle);
	        uCommunity.setPostContent(postContent);        
	        
	        // 파일이 업로드되었는지 확인
	        if (postFile != null && !postFile.isEmpty()) {
	        	// 파일이 업로드된 경우에 대한 처리
	        	// 파일 저장 등
	        }
	        
	        // 게시글 데이터를 서비스로 전달 후 DB에 저장
	        uCommunityService.postSave(postCategory, postTitle, postContent, postFile);
	        return "redirect:/community"; 
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	        
	        return "redirect:/error";
	        
	        // return "error";
	    }
	}
	
	
	
	
	// 게시글 작성 폼 이동
	@GetMapping("/postWrite")
	public String postWrite(Model model) {
		
		model.addAttribute("title", "게시글 작성");
		return "user/board/postWrite";
	
	}
	
	// 특정 게시글 상세 조회
	@GetMapping("/postDetail")
	public String postDetail(@RequestParam(name = "postNum", required = false) String postNum, Model model) {
		UCommunity postDetail = uCommunityService.getPostByPostNum(postNum);
	    List<UCommunity> commentList = uCommunityService.getCommentByPostNum(postNum); // 해당 게시글의 모든 댓글을 가져오는 메서드 호출

	    model.addAttribute("commentList", commentList);
	    model.addAttribute("postDetail", postDetail);
	    model.addAttribute("postTitle", postDetail.getPostTitle());
	    model.addAttribute("postContent", postDetail.getPostContent());
	    model.addAttribute("title", "게시글 상세");
	    
	    log.info("postNum: {}", postNum);
	    log.info("getPostByPostNum : {}", postDetail);
	    log.info("getCommentByPostNum : {}", commentList);

	    return "user/board/postDetail";
	}
	
	
	
	//게시글 조회수 증가
   @PostMapping("/increaseViewCount")
    public String increaseViewCount(@RequestParam("postNum") String postNum) {
        uCommunityService.increaseViewCount(postNum);
        return "redirect:/postDetail"; // 증가된 조회수를 반영한 게시물 상세 페이지로 리다이렉트
    }
   
   
   // 답글 저장
   @PostMapping("/replySave")
   public String replySave(String replyContent) {
       // 클라이언트로부터 받은 답글을 서비스에 전달하여 저장하고 결과를 반환
       return uCommunityService.replySave(replyContent);
   }
	

}
