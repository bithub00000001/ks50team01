package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PFaq;
import ksmart.ks50team01.platform.board.service.PFaqService;
import ksmart.ks50team01.user.board.dto.UFaq;
import ksmart.ks50team01.user.board.service.UFaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/faq")
@RequiredArgsConstructor
@Slf4j
public class UFaqController {
	
	private final UFaqService uFaqService;
	
	// 자주찾는 질문 조회 페이지
	@GetMapping({"/",""})
	public String faqList(Model model) {
		List<UFaq> faqList = uFaqService.getFaqList();
		log.info("faqList: {}", faqList);
		model.addAttribute("faqList", faqList);
		model.addAttribute("title", "자주찾는질문");
		return "user/board/faqList";
	}
	
}
