package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/board")
public class UNoticeController {
	
	@GetMapping("/notice")
	public String notice(Model model) {
		
		model.addAttribute("title", "공지사항페이지");
		return "user/board/noticeList";
		
	}
	
	

}
