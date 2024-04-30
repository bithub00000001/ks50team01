package ksmart.ks50team01.platform.partnership.ranking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class PartnerShipRangkingController {

	@GetMapping("/platformranking/platformranking")
	public String Partnershipranking(Model model) {
		
		model.addAttribute("title", "추천페이지");
		return "/user/platformranking/platformranking";
	}
}
