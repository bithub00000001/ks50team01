package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PFaq;
import ksmart.ks50team01.platform.board.service.PFaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PFaqController {
	
	private final PFaqService pFaqService;
	
	// 자주찾는 질문 수정 POST 요청
	@PostMapping("/faqModify")
	public String faqModify(PFaq PFaq, Model model) {
		model.addAttribute("title", "자주찾는 질문 수정 페이지");
		return "redirect:/platform/board/faqList";
	}
	
	// 자주찾는 질문 수정 페이지
	@GetMapping("/faqModify")
	public String faqModify(Model model) {
		model.addAttribute("title", "자주찾는 질문 수정");
		return "platform/board/faqModify";
	}
	
	// 자주찾는 질문 작성 POST 요청
		@PostMapping("/faqWrite")
		public String faqWrite(PFaq PFaq, Model model) {
			model.addAttribute("title", "자주찾는 질문 작성 페이지");
			return "redirect:/platform/board/faqList";
		}
	
	// 자주찾는 질문 작성 페이지
	@GetMapping("/faqWrite")
	public String faqWrite(Model model) {
		model.addAttribute("title", "자주찾는 질문 작성");
		return "platform/board/faqWrite";
	}
	
	// 자주찾는 질문 조회 페이지
	@GetMapping("/faqList")
	public String faqList(Model model) {
		List<PFaq> faqList = pFaqService.getFaqList();
		log.info("faqList: {}", faqList);
		model.addAttribute("faqList", faqList);
		model.addAttribute("title", "자주찾는 질문 조회");
		return "platform/board/faqList";
	}
}
