package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/qna")
public class UQnaController {
	
	@GetMapping({"/",""})
	public String qna(Model model) {
		
		model.addAttribute("title", "1:1문의");
		return "user/board/qnaList";
		
	}
	
	@GetMapping("/qnaWrite")
	public String qnaWrite(Model model) {
		
		model.addAttribute("title", "1:1문의 작성");
		return "user/board/qnaWrite";
		
	}

}
