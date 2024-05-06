package ksmart.ks50team01.user.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class CommonController {

	@GetMapping(value = {"main", "/main"})
	public String userMain(Model model) {
		model.addAttribute("title", "미정 플래너");
		return "user/main";
	}

	@GetMapping(value = {"/refer"})
	public String main(Model model) {
		model.addAttribute("title", "미정 플래너");
		return "user/userRefGuide";
	}

	@GetMapping("/refer2")
	public String userRefer(Model model) {
		model.addAttribute("title", "유저 페이지 레퍼런스");
		return "user/referPage2";
	}
}
