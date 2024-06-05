package ksmart.ks50team01.platform.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.board.dto.PAnswer;
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
	public String qnaList(@RequestParam(value = "category", required = false) String category, Model model) {
	    List<PQna> qnaList;
	    
	    if (category != null) {
	        qnaList = pQnaService.getQnaListByCategory(category);
	    } else {
	        qnaList = pQnaService.getQnaList();
	    }
	    
		/*
	    // <br> 태그를 \n으로 변환
	    for (PQna pQna : qnaList) {
	        if (pQna.getQnaContent() != null) {
	            String contentWithLineBreaks = pQna.getQnaContent().replace("<br>", "\n");
	            pQna.setQnaContent(contentWithLineBreaks);
	        }
	    } */

		log.info("qnaList: {}", qnaList);
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("selectedCategory", category);
		model.addAttribute("title", "1:1문의 조회");
		
		return "platform/board/qnaList";
	}
	
	
	
    @GetMapping("/qnaDetail")
    public PQna getQnaDetail(@RequestParam("qnaNum") String qnaNum) {
    	PQna qnaDetail = pQnaService.getQnaDetailByNum(qnaNum);
        return qnaDetail;
    }
    

    
    // 1:1문의 답변 저장
    @PostMapping("/answerSave")
    @ResponseBody
    public Map<String, Object> answerSave(@RequestBody PAnswer pAnswer) {
        Map<String, Object> response = new HashMap<>();
        try {
        	pQnaService.answerSave(pAnswer.getQnaNum(), pAnswer.getAnsRegId(), pAnswer.getAnsContent());
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        log.info("ansRegId:{}" ,pAnswer.getAnsRegId());
        return response;
    }
    
    
}



