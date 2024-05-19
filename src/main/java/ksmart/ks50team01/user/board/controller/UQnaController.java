package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.user.board.dto.UQna;
import ksmart.ks50team01.user.board.mapper.UQnaMapper;
import ksmart.ks50team01.user.board.service.UQnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/qna")
@RequiredArgsConstructor
@Slf4j
public class UQnaController {
	
	private final UQnaService uQnaService;
	
	// 1:1문의 조회
	@GetMapping({"/",""})
	public String qna(Model model) {
		List<UQna> qnaList = uQnaService.getQnaList();
		log.info("qnaList: {}", qnaList);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("title", "1:1문의");
		return "user/board/qnaList";
		
	}
	
	// 1:1문의 상세 조회
	@GetMapping("/qnaDetail")
	public String qnaDetail(@RequestParam(name = "qnaNum", required = false) String qnaNum, Model model) {
		UQna qnaDetail = uQnaService.getQnaByQnaNum(qnaNum);
		
		model.addAttribute("qnaDetail", qnaDetail);
		model.addAttribute("qnaTitle", qnaDetail.getQnaTitle());
		model.addAttribute("qnaContent", qnaDetail.getQnaContent());
		model.addAttribute("faqCateType", qnaDetail.getFaqCateType());
		
		log.info("qnaNum: {}", qnaNum);
		log.info("getQnaByQnaNum: {}", qnaDetail);
		model.addAttribute("title", "1:1문의 상세 페이지");
		return "user/board/qnaDetail";
	}
	
	// 1:1문의 작성
	@GetMapping("/qnaWrite")
	public String qnaWrite(Model model) {
		model.addAttribute("title", "1:1문의 작성");
		return "user/board/qnaWrite";
		
	}

}
