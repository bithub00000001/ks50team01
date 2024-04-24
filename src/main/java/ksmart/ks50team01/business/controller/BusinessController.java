package ksmart.ks50team01.business.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.business.dto.Business;
import ksmart.ks50team01.business.service.BusinessService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/business")
public class BusinessController {
	
	private final BusinessService businessService; 

	@GetMapping("/list")
	public String getBusinessList(Model model) {
		List<Business> businessList = businessService.getBusinessList();
		model.addAttribute("title", "사업주 현황");
		model.addAttribute("businessList", businessList);
		
		return "business/list";
	}
}






