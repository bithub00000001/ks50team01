package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "platform/board")
public class PCommunityController {
	
	@GetMapping("/communityList")
	public String communityList(Model model) {
		
		model.addAttribute("title", "커뮤니티");
		return "platform/board/communityList";
	}

}
