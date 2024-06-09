package ksmart.ks50team01.user.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.user.board.service.UFaqService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/faq")
@RequiredArgsConstructor
public class UFaqController {
	
	private final UFaqService uFaqService;
	
	// 자주 묻는 질문 조회 페이지
	@GetMapping({"/",""})
	public String faqList(@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage,
			Model model) {
		//List<UFaq> faqList = uFaqService.getFaqList();
		
		Map<String, Object> resultMap = uFaqService.getFaqList(currentPage);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> faqList = (List<Map<String, Object>>) resultMap.get("faqList");
		int lastPage = (int) resultMap.get("lastPage");
		int startPageNum = (int) resultMap.get("startPageNum");
		int lastPageNum = (int) resultMap.get("lastPageNum");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		
		model.addAttribute("faqList", faqList);
		model.addAttribute("title", "자주 묻는 질문");
		
		return "user/board/faqList";
	}
	
}
