package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.board.dto.PQna;
import ksmart.ks50team01.platform.board.service.PQnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PQnaController {
	
	private final PQnaService pQnaService;
	

	// 1:1문의 조회 페이지
	@GetMapping("/qnaList")
	public String qnaList(Model model) {
		List<PQna> qnaList = pQnaService.getQnaList();
		log.info("qnaList: {}", qnaList);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("title", "1:1문의 조회");
		return "platform/board/qnaList";
	}
	
    @GetMapping("/qnaDetail")
    public PQna getQnaDetail(@RequestParam("qnaNum") String qnaNum) {
    	PQna qnaDetail = pQnaService.getQnaDetailByNum(qnaNum);
        return qnaDetail;
    }
}


