package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PFaq;
import ksmart.ks50team01.platform.board.service.PFaqService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(value = "/platform/board")
@Slf4j
public class PFaqController {
	
	private final PFaqService pFaqService;
	
	public PFaqController(PFaqService pFaqService) {
		this.pFaqService = pFaqService;
	}
	
	@GetMapping("/faqList")
	public String faqList(Model model) {
		List<PFaq> faqList = pFaqService.getFaqList();
		log.info("faqList: {}", faqList);
		model.addAttribute("faqList", faqList);
		model.addAttribute("title", "자주찾는 질문");
		
		return "platform/board/faqList";
	}
	
	@GetMapping("/faqWrite")
	public String faqWrite(Model model) {
		
		model.addAttribute("title", "자주찾는질문 작성");
		return "platform/board/faqWrite";
	}
	
	@GetMapping("/faqModify")
	public String faqModify(Model model) {
		
		model.addAttribute("title", "자주찾는질문 수정");
		return "platform/board/faqModify";
	}
	
	

}
