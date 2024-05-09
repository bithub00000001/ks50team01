package ksmart.ks50team01.platform.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platform/board")
public class PCategoryController {
	
	@GetMapping("/categoryList")
	public String faqList(Model model) {
		
		model.addAttribute("title", "카테고리 관리");
		return "platform/board/faqList";
	}

}
