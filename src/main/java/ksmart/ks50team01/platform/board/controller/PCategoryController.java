package ksmart.ks50team01.platform.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.service.PCategoryService;
import ksmart.ks50team01.platform.board.service.PNoticeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/platform/board")
@Slf4j
public class PCategoryController {
	
	private final PCategoryService pCategoryService;
	
	public PCategoryController(PCategoryService pCategoryService) {
		this.pCategoryService = pCategoryService;
	}
	
	
	@GetMapping("/categoryList")
	public String faqList(Model model) {
		
		model.addAttribute("title", "카테고리 관리");
		return "platform/board/faqList";
	}

}
