package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PQna;
import ksmart.ks50team01.user.board.dto.UQna;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/qna")
@RequiredArgsConstructor
@Slf4j
public class UQnaController {
	
	// 1:1문의 조회
	@GetMapping({"/",""})
	public String qna(Model model) {
		List<UQna> qnaList = uQnaService.getQnaList();
		log.info("qnaList: {}", qnaList);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("title", "1:1문의");
		return "user/board/qnaList";
		
	}
	
	@GetMapping("/qnaWrite")
	public String qnaWrite(Model model) {
		model.addAttribute("title", "1:1문의 작성");
		return "user/board/qnaWrite";
		
	}

}
