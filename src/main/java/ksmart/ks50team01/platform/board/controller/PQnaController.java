package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PNotice;
import ksmart.ks50team01.platform.board.dto.PQna;
import ksmart.ks50team01.platform.board.service.PNoticeService;
import ksmart.ks50team01.platform.board.service.PQnaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/platform/board")
@Slf4j
public class PQnaController {
	
	private final PQnaService pQnaService;
	
	public PQnaController(PQnaService pQnaService) {
		this.pQnaService = pQnaService;
	}
	
	@GetMapping("/qnaList")
	public String qnaList(Model model) {
		List<PQna> qnaList = pQnaService.getQnaList();
		
		model.addAttribute("title", "1:1문의");
		return "platform/board/qnaList";
		
	}

}
