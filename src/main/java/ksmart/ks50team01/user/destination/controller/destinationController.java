package ksmart.ks50team01.user.destination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class destinationController {
	
	@GetMapping("/destination/lodgingCheck")
	public String lodgingCheck(Model model) {
		
		model.addAttribute("title", "숙소 조회");
		
		return "/user/destination/lodgingCheck";
	}
	
	@GetMapping("/destination/lodgingCheckDetails")
	public String lodgingCheckDetails(Model model) {
		
		model.addAttribute("title", "숙소 세부사항");
		
		return "/user/destination/lodgingCheckDetails";
	}
	
	@GetMapping("/destination/restaurantCheck")
	public String restaurantCheck(Model model) {
		
		model.addAttribute("title", "음식점 조회");
		
		return "/user/destination/restaurantCheck";
	}
	
	@GetMapping("/destination/restaurantCheckDetails")
	public String restaurantCheckDetails(Model model) {
		
		model.addAttribute("title", "음식점 세부사항");
		
		return "/user/destination/restaurantCheckDetails";
	}
	
	@GetMapping("/destination/tourCheck")
	public String tourCheck(Model model) {
		model.addAttribute("title", "관광지 조회");
		
		return "/user/destination/tourCheck";
	}
	
	@GetMapping("/destination/tourCheckDetails")
	public String tourCheckDetails(Model model) {
		
		model.addAttribute("title", "관광지 세부사항");
		
		return "/user/destination/tourCheckDetails";
	}
	
	/*
	 * @GetMapping("/destination/regionalCheck") public String regionalCheck(Model
	 * model) {
	 * 
	 * model.addAttribute("title", "지역 조회");
	 * 
	 * return "/user/destination/regionalCheck"; }
	 * 
	 * @GetMapping("/destination/regionalCheckDetails") public String
	 * regionalCheckDetails(Model model) {
	 * 
	 * model.addAttribute("title", "지역 세부 조회");
	 * 
	 * return "/user/destination/regionalCheckDetails"; }
	 */

}
