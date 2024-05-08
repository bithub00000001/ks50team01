package ksmart.ks50team01.platform.destination.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.destination.dto.Destination;
import ksmart.ks50team01.platform.destination.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(value = "/platform")
@RequiredArgsConstructor
public class DestinationManageController {
	
	private final DestinationService destinationService;
	
	@PostMapping("/destination/tourModify")
	public String tourModifyProcess(@RequestBody Destination destination) {
		destinationService.updateTourInfo(destination);
		
		return "redirect:/platform/destination/tourManageChange";
	}
	
	
	@GetMapping("/destination/tourManage")
	public String tourManage(Model model) {
		List<Destination> tourInfoList = destinationService.getTourInfoList();
		
		model.addAttribute("title","관광지 관리");
		model.addAttribute("tourInfoList", tourInfoList);
		
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
	
	@GetMapping("/destination/categoryManage")
	public String categoryManage(Model model) {
		
		model.addAttribute("title", "카테고리 관리");
		
		return "/platform/destination/categoryManage";
		
	}

}
