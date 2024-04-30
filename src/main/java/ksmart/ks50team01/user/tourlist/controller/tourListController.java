package ksmart.ks50team01.user.tourlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class tourListController {
	
	@GetMapping("/tour/tourList")
	public String tourList(Model model) {
		
		model.addAttribute("title", "관광지 리스트");
		
		return "/user/tour/tourList";
	}
	
	@GetMapping("/tour/tourListDetails")
	public String tourListDetails(Model model) {
		
		model.addAttribute("title", "관광지 세부사항");
		
		return "/user/tour/tourListDetails";
	}
	

}
