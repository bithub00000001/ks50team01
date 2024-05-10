package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.service.PCategoryService;
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
		List<PCategory> faqCategoryList = pCategoryService.getFaqCategoryList();
		log.info("faqCategoryList:{}", faqCategoryList);
		
		model.addAttribute("title", "카테고리 관리");
		model.addAttribute("faqCategoryList", faqCategoryList);
		return "platform/board/categoryList";
	}
	
	@GetMapping("/categoryModify")
	public String categoryModify(Model model) {
		
		model.addAttribute("title", "카테고리 수정");
		
		return "platform/board/categoryModify";
	}
	
	@GetMapping("/categoryAdd")
	public String categoryAdd(Model model) {
		
		model.addAttribute("title", "카테고리 추가");
		
		return "platform/board/categoryAdd";
	}
	
	
	
	

}
