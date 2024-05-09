package ksmart.ks50team01.platform.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(value = "/platform/board")
public class PReportController {
	
	@GetMapping("reportList")
	public String reportList(Model model) {
		
		model.addAttribute("title", "게시판 신고내역");
		return "platform/board/reportList";
	}
	

}
