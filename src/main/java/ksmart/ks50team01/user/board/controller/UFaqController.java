package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/user/board")
public class UFaqController {
	
	@GetMapping("/faqList")
	public String faq(Model model) {
		
		model.addAttribute("title", "자주찾는질문페이지");
		return "user/board/faqList";
	}
	
}
