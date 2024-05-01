package ksmart.ks50team01.platform.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platform/board")
public class PNoticeController {
	
	@GetMapping("/noticeWrite")
	public String noticeWrite(Model model) {
		
		model.addAttribute("title", "공지사항작성페이지");
		return "platform/board/noticeWrite";
		
	}

}
