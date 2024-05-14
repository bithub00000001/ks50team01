package ksmart.ks50team01.user.board.controller;

import java.util.Collections;
import java.util.List;

import javax.xml.stream.events.Comment;

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
		model.addAttribute("postList", postList); // postList를 모델에 추가
		model.addAttribute("title", "커뮤니티");
		return "user/board/postList";
		
	
	}
	
	
	@PostMapping("/postSave")
	public String write(@RequestParam("file") MultipartFile file,
            			@RequestParam("postTitle") String postTitle,
            			@RequestParam("postContent") String postContent,
            			RedirectAttributes redirectAttributes) {
	    try {
	        // 입력된 제목과 내용을 UCommunity 객체에 설정
	        UCommunity uCommunity = new UCommunity();
	        uCommunity.setPostTitle(postTitle);
	        uCommunity.setPostContent(postContent);		
			
			
			// 게시글 데이터와 첨부파일을 서비스로 전달 후 DB에 저장
			uCommunityService.postSave(uCommunity, file);
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
	

}
