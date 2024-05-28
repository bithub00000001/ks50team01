package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.user.board.dto.UCategory;
import ksmart.ks50team01.user.board.dto.UQna;
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
		model.addAttribute("getQnaAnswer", qnaDetail.getQnaAnswer());
		
		log.info("qnaNum: {}", qnaNum);
		log.info("getQnaByQnaNum: {}", qnaDetail);
		model.addAttribute("title", "1:1문의 상세 페이지");
		return "user/board/qnaDetail";
	}
	
	
	// 1:1문의 작성 POST 요청
	@PostMapping("/qnaWrite")
	public String qnaWrite(UQna uQna, Model model)	{
		String contentWithLineBreaks = uQna.getQnaContent().replace("\n", "<br>");
		uQna.setQnaContent(contentWithLineBreaks);
		
		uQnaService.insertQna(uQna);
		
		log.info("QNA 등록:{}", uQna);
		
		return "redirect:/qna";
	}
	
	

	
	// 1:1문의 작성 폼 이동
	@GetMapping("/qnaWrite")
	public String qnaWrite(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null) {
			model.addAttribute("loginRequired", true);
			return "redirect:/trip"; // 로그인 페이지 경로로 변경
		}
		
		List<UCategory> qnaCateList = uQnaService.getQnaCateList();
		log.info("qnaCateList: {}", qnaCateList);
		
		model.addAttribute("title", "1:1문의 작성");
		model.addAttribute("qnaCateList", qnaCateList);
		
		return "user/board/qnaWrite";
		
	}
	
	// 1:1문의 수정 POST 요청
	@PostMapping("/qnaModify")
	public String qnaModify(UQna uQna, Model model) {
		String contentWithLineBreaks = uQna.getQnaContent().replace("\n", "<br>");
		uQna.setQnaContent(contentWithLineBreaks);
		
		uQnaService.qnaModify(uQna);
		
		log.info("1:1문의 수정", uQna);
		
		// 수정된 1:1문의 상세 페이지로 이동
		return "redirect:/qna/qnaDetail?qnaNum=" + uQna.getQnaNum();
		
	}
	
	// 1:1문의 수정 페이지
	@GetMapping("/qnaModify")
	public String qnaModify(@RequestParam(value = "qnaNum") String qnaNum, Model model) {
		UQna qnaInfo = uQnaService.getQnaInfoByNum(qnaNum);
		
	    // <br> 태그를 \n으로 변환
	    String contentWithLineBreaks = qnaInfo.getQnaContent().replace("<br>", "\n");
	    qnaInfo.setQnaContent(contentWithLineBreaks);
		
		log.info("qnaInfo :{}", qnaInfo);
		
		model.addAttribute("qnaInfo", qnaInfo);
		model.addAttribute("title", "1:1문의 수정 페이지");
		
		return "user/board/qnaModify";
	}
	
	// 1:1문의 삭제 POST 요청
	@PostMapping("/qnaDelete")
	public String qnaDelete(@RequestParam (value = "qnaNum") String qnaNum, Model model) {
		
		uQnaService.qnaDelete(qnaNum);
		
		model.addAttribute("qnaNum", qnaNum);
		model.addAttribute("title", "1:1문의 삭제");
		
		return "redirect:/qna";
		
	}
		

}
