package ksmart.ks50team01.platform.statistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/platform/statistic")
@RequiredArgsConstructor
public class PStatisticController {
	
	@GetMapping("/member")
	public String meberTotal(Model model) {

		
		model.addAttribute("title", "회원 가입 및 탈퇴 조회");
		
		
		return "platform/statistic/member";
	}

}
