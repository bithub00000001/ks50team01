package ksmart.ks50team01.user.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 1:1문의 목록 조회
	@GetMapping({"/",""})
	public String qnaList(@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage,
			Model model) {
		//List<UQna> qnaList = uQnaService.getQnaList();
		
		Map<String, Object> resultMap = uQnaService.getQnaList(currentPage);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qnaList = (List<Map<String, Object>>) resultMap.get("qnaList");
		int lastPage = (int) resultMap.get("lastPage");
		int startPageNum = (int) resultMap.get("startPageNum");
		int lastPageNum = (int) resultMap.get("lastPageNum");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("title", "1:1문의");
		
		return "user/board/qnaList";
	}
	
	// 1:1문의 상세 조회
	@GetMapping("/qnaDetail")
	public String qnaDetail(@RequestParam(name = "qnaNum", required = false) String qnaNum, Model model) {
		UQna qnaDetail = uQnaService.getQnaDetail(qnaNum);
		
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
	@PostMapping("/qnaAdd")
	public String qnaAdd(UQna uQna, Model model)	{
		String contentWithLineBreaks = uQna.getQnaContent().replace("\n", "<br>");
		uQna.setQnaContent(contentWithLineBreaks);
		
		uQnaService.qnaAdd(uQna);
		
		log.info("QNA 등록:{}", uQna);
		
		return "redirect:/qna";
	}
	
	
	// 1:1문의 작성 폼 이동
	@GetMapping("/qnaAdd")
	public String qnaAdd(Model model) {
		
		List<UCategory> qnaCateList = uQnaService.getQnaCateList();
		log.info("qnaCateList: {}", qnaCateList);
		
		model.addAttribute("title", "1:1문의 작성");
		model.addAttribute("qnaCateList", qnaCateList);
		
		return "user/board/qnaAdd";
		
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
	@PostMapping("/qnaRemove")
	public String qnaRemove(@RequestParam (value = "qnaNum") String qnaNum, Model model) {
		
		uQnaService.qnaRemove(qnaNum);
		
		model.addAttribute("qnaNum", qnaNum);
		model.addAttribute("title", "1:1문의 삭제");
		
		return "redirect:/qna";
		
	}
		

}
