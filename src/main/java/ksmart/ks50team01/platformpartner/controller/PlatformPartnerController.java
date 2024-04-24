package ksmart.ks50team01.platformpartner.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platformpartner.dto.PlatformPartner;
import ksmart.ks50team01.platformpartner.service.PlatformPartnerService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/platformPartner")
@RequiredArgsConstructor
public class PlatformPartnerController {
	
	private final PlatformPartnerService platformPartnerService;

	@GetMapping("/platformPartnerList")
	public String getPlatformPartnerList(Model model) {
		List<PlatformPartner> platformPartnerList = platformPartnerService.getPlatformPartnerList();
		
		model.addAttribute("title", "플랫폼관리자추천");
		model.addAttribute("platformPartnerList", platformPartnerList);
		return "platformPartner/platformPartnerList";
	}
}
