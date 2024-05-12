package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmart.ks50team01.user.board.dto.UCommunity;
import ksmart.ks50team01.user.board.service.UCommunityService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value = "/community")
@RequiredArgsConstructor
public class UCommunityController {
	
	private final UCommunityService uCommunityService;
	
	// 게시글 목록 조회
	@GetMapping({"/",""})
	public String postList(Model model) {
		List<UCommunity> postList = uCommunityService.getPostList();
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
			// 예외 발생 시 에러 메시지를 리다이렉트할 페이지로 전달하여 이동
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/error";
            
            // /error 경로에 매핑된 뷰를 렌더링
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
	@GetMapping("/postDetail/{postNum}")
	public String postDetail(@PathVariable String postNum, Model model) {
		UCommunity postDetail = uCommunityService.getPostById(postNum);
		model.addAttribute("postTitle", "게시글 제목");
		model.addAttribute("postContent", "게시글 내용");
		model.addAttribute("title", "게시글 상세");
		return "user/board/postDetail";
	}
	
	
	
	
	
	
	
	
	
	

}
