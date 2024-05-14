package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/report")
public class UReportController {
	
	@GetMapping({"/",""})
	public String report(Model model) {
		
		model.addAttribute("title", "신고");
		return "user/board/report";
	}
	
}
