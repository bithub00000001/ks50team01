package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/board")
public class UCommunityController {
	
	@GetMapping("/community")
	public String getMethodName(Model model) {
		
		model.addAttribute("title", "커뮤니티페이지");
		return "user/board/communityList";
	
	}

}
