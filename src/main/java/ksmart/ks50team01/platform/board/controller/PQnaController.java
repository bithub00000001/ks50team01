package ksmart.ks50team01.platform.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platform/board")
public class PQnaController {
	
	@GetMapping("/qnaList")
	public String qnaList(Model model) {
		
		model.addAttribute("title", "1:1문의페이지");
		return "platform/board/qnaList";
		
	}

}
