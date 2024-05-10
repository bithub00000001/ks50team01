package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value = "/community")
public class UCommunityController {
	
	@GetMapping({"/",""})
	public String communityList(Model model) {
		
		model.addAttribute("title", "커뮤니티");
		return "user/board/communityList";
	
	}
	
	@GetMapping("/postWrite")
	public String postWrite(Model model) {
		
		model.addAttribute("title", "게시글 작성");
		return "user/board/postWrite";
	
	}
	
	@GetMapping("/postDetail")
	public String postDetail(Model model) {
		
		model.addAttribute("postTitle", "게시글 제목");
		model.addAttribute("postContent", "게시글 내용");
		model.addAttribute("title", "게시글 상세");
		return "user/board/postDetail";
	}
	
	
	@PostMapping("/writeProcess")
	public String postwriteProcess(String postTitle, String postContent) {
		
		System.out.println("제목 : " + postTitle);
		System.out.println("내용 : " + postContent);
		return "";
	}
	

}
