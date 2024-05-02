package ksmart.ks50team01.user.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/user/board")
public class UReportController {
	
	@GetMapping("/report")
	public String report(Model model) {
		
		return "user/board/report";
	}
	
}
