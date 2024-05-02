package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user/board")
public class UQnaController {
	
	@GetMapping("/qnaList")
	public String qna(Model model) {
		
		model.addAttribute("title", "1:1문의내역페이지");
		return "user/board/qnaList";
		
	}
	
	@GetMapping("/qnaWrite")
	public String qnaWrite(Model model) {
		
		model.addAttribute("title", "1:1문의글쓰기");
		return "user/board/qnaWrite";
		
	}

}
