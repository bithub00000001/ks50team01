package ksmart.ks50team01.platform.destination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/platform")
public class tourManageController {
	
	@GetMapping("/destination/tourManage")
	public String tourManage(Model model) {
		
		model.addAttribute("title","관광지 관리");
		
		return "/platform/destination/tourManage";
	}
	
	@GetMapping("/destination/lodgingManage")
	public String lodgingManage(Model model) {
		
		model.addAttribute("title", "숙소 관리");
		
		return "/platform/destination/lodgingManage";
	
	}
	
	@GetMapping("/destination/restaurantManage")
	public String restaurantManage(Model model) {
		
		model.addAttribute("title", "식당 관리");
		
		return "/platform/destination/restaurantManage";
	}
	
	@GetMapping("/destination/destinationRegister")
	public String destinationRegister(Model model) {
		
		model.addAttribute("title", "여행지 일괄등록");
		
		return "/platform/destination/destinationRegister";
	}

}
