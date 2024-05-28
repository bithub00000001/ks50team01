package ksmart.ks50team01.platform.board.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.dto.PFaq;
import ksmart.ks50team01.platform.board.service.PFaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PFaqController {
	
	private final PFaqService pFaqService;
	
	// 자주찾는질문 수정 POST 요청
	@PostMapping("/faqModify")
	public String faqModify(PFaq pFaq, Model model) {
        String contentWithLineBreaks = pFaq.getFaqContent().replace("\n", "<br>");
        pFaq.setFaqContent(contentWithLineBreaks);
		
        pFaqService.faqModify(pFaq);
        
		log.info("자주찾는질문 수정: {}", pFaq);
		
		model.addAttribute("title", "자주찾는질문 수정");
		
		return "redirect:/platform/board/faqList";
	}
	

	
	
	// 자주찾는질문 수정 페이지
	@GetMapping("/faqModify")
	public String faqModify(@RequestParam(value = "faqNum") String faqNum, Model model) {
		PFaq faqInfo = pFaqService.getFaqInfoByNum(faqNum);
		
	    // <br> 태그를 \n으로 변환
	    String contentWithLineBreaks = faqInfo.getFaqContent().replace("<br>", "\n");
	    faqInfo.setFaqContent(contentWithLineBreaks);
	    
		log.info("faqInfo : {}", faqInfo);
		
		model.addAttribute("faqInfo", faqInfo);
		model.addAttribute("title", "자주찾는질문 수정 페이지");
		
		return "platform/board/faqModify";
	}
	
 
	

	
	// 자주찾는질문 조회 페이지
	@GetMapping("/faqList")
	public String faqList(Model model) {
		List<PFaq> faqList = pFaqService.getFaqList();
		log.info("faqList: {}", faqList);
		model.addAttribute("faqList", faqList);
		model.addAttribute("title", "자주찾는 질문 조회");
		return "platform/board/faqList";
	}
	
	
	
	// 자주찾는질문 작성 POST 요청
    @PostMapping("/faqWrite")
    public String faqWrite(PFaq pFaq, Model model) {
        String contentWithLineBreaks = pFaq.getFaqContent().replace("\n", "<br>");
        pFaq.setFaqContent(contentWithLineBreaks);

        pFaqService.insertFaq(pFaq);

        model.addAttribute("title", "자주찾는질문 작성");

        return "redirect:/platform/board/faqList";
    }
    

	// 자주찾는질문 작성 페이지
	@GetMapping("/faqWrite")
	public String faqWrite(Model model) {
		List<PCategory> faqCateList = pFaqService.getfaqCateList();
		log.info("자주찾는질문 카테고리 조회 결과: {}", faqCateList);
		
	    // 현재 날짜를 포맷에 맞게 설정
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String currentDate = LocalDate.now().format(formatter);
	    
	    
	    // 모델에 현재 날짜 추가
	    model.addAttribute("currentDate", currentDate);
	    model.addAttribute("faqCateList", faqCateList);
		model.addAttribute("title", "자주찾는질문 작성 페이지");
		
		return "platform/board/faqWrite";
	}
		
	
	
	
	// 자주찾는 질문 삭제 POST 요청
	@PostMapping("/faqDelete")
	public String faqDelete(@RequestParam(value = "faqNum") String faqNum, Model model) {
	    
		pFaqService.faqDelete(faqNum);
		
		model.addAttribute("faqNum", faqNum);
		model.addAttribute("title", "자주찾는질문 삭제");
		
		return "redirect:/platform/board/faqList";
	}
	
	// 자주찾는 질문 삭제
	@GetMapping("/faqDelete")
	public String faqDeletePage(@RequestParam(value = "faqNum") String faqNum, Model model) {
		
		List<PFaq> faqList = pFaqService.getFaqList();
		pFaqService.faqDelete(faqNum);
		
		model.addAttribute("faqList", faqList);
		model.addAttribute("faqNum", faqNum);
		model.addAttribute("title", "자주찾는질문 삭제");
		
		return "redirect:/platform/board/faqList";
	}
	
	
	

	
	

}
