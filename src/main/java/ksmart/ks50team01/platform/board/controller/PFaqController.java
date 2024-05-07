package ksmart.ks50team01.platform.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/platform/board")
public class PFaqController {
	
	@GetMapping("/faqList")
	public String faqList(Model model) {
		
		model.addAttribute("title", "자주찾는질문");
		return "platform/board/faqList";
	}
	
	@GetMapping("/faqWrite")
	public String faqWrite(Model model) {
		
		model.addAttribute("title", "자주찾는질문작성페이지");
		return "platform/board/faqWrite";
	}
	
	

}
