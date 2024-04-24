package ksmart.ks50team01.admin.total.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.admin.total.service.TotalService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/total")
@RequiredArgsConstructor
public class TotalController {
	private final TotalService totalService;
	
	@GetMapping("/memberTotal")
	public String meberTotal(Model model) {
		int newMemTotal = totalService.getNewMemeberTotal();
		int drawnMemTotal = totalService.getWithdrawnMemeberTotal();
		
		model.addAttribute("title", "회원 가입 및 탈퇴 조회");
		
		model.addAttribute("newMemTotal", newMemTotal);
		model.addAttribute("drawnMemTotal", drawnMemTotal);
		
		
		return "admin/total/memberTotal";
	}

}
