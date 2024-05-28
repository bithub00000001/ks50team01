package ksmart.ks50team01.platform.board.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.board.dto.PCategory;
import ksmart.ks50team01.platform.board.dto.PNotice;
import ksmart.ks50team01.platform.board.service.PNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PNoticeController {
	
	private final PNoticeService pNoticeService;
	
	
	// 공지사항 조회 페이지
	@GetMapping("/noticeList")
	public String noticeList(Model model) {
	    List<PNotice> noticeList = pNoticeService.getNoticeList();
	    log.info("noticeList: {}", noticeList);
	    model.addAttribute("noticeList", noticeList);
		model.addAttribute("title", "공지사항 조회");
		return "platform/board/noticeList";
	}

	// 공지사항 작성 POST 요청
	@PostMapping("/noticeWrite")
	public String noticeWrite(PNotice pNotice, Model model) {
		String contentWithLineBreaks = pNotice.getNoticeContent().replace("\n", "<br>");
		pNotice.setNoticeContent(contentWithLineBreaks);
        
	    pNoticeService.insertNotice(pNotice);
	    
	    model.addAttribute("title", "공지사항 작성");
	    
	    return "redirect:/platform/board/noticeList";
	}
	
	// 공지사항 작성 폼 이동
	@GetMapping("/noticeWrite")
	public String noticeWrite(Model model) {
		List<PCategory> noticeCateList = pNoticeService.getNoticeCateList();
		log.info("noticeCateList: {}", noticeCateList);
		
		// 현재 날짜를 포맷에 맞게 설정
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String currentDate = LocalDate.now().format(formatter);
		
	    // 모델에 현재 날짜 추가
	    model.addAttribute("currentDate", currentDate);
		model.addAttribute("noticeCateList", noticeCateList);
		model.addAttribute("title", "공지사항 작성 페이지");
		
		return "platform/board/noticeWrite";
	}
	
	// 공지사항 수정 POST 요청
		@PostMapping("/noticeModify")
		public String noticeModify(PNotice pNotice, Model model) {
			String contentWithLineBreaks = pNotice.getNoticeContent().replace("\n", "<br>");
			pNotice.setNoticeContent(contentWithLineBreaks);
			
			pNoticeService.noticeModify(pNotice);
			
			log.info("공지사항 수정: {}", pNotice);
			
			model.addAttribute("title", "공지사항 수정");
			
			return "redirect:/platform/board/noticeList";
		}
	
	// 공지사항 수정 페이지
	@GetMapping("/noticeModify")
	public String noticeModify(@RequestParam(value = "noticeNum") String noticeNum, Model model) {
		PNotice noticeInfo = pNoticeService.getNoticeInfoByNum(noticeNum);
		
	    // <br> 태그를 \n으로 변환
	    String contentWithLineBreaks = noticeInfo.getNoticeContent().replace("<br>", "\n");
	    noticeInfo.setNoticeContent(contentWithLineBreaks);
		
		log.info("noticeInfo : {}", noticeInfo);
		
		model.addAttribute("noticeInfo", noticeInfo);
		model.addAttribute("title", "공지사항 수정 페이지");
		
		return "platform/board/noticeModify";
	}
	
	// 공지사항 삭제 POST 요청
	@PostMapping("/noticeDelete")
	public String noticeDelete(@RequestParam(value="noticeNum") String noticeNum, Model model) {
		pNoticeService.noticeDelete(noticeNum);
		
		model.addAttribute("title", "공지사항 삭제");
		model.addAttribute("noticeNum", noticeNum);
		
		return "redirect:/platform/board/noticeList";
	}
	
	
	/*
	// 공지사항 삭제
	@GetMapping("/noticeDelete")
	public String noticeDeletePage(@RequestParam(value="noticeNum") String noticeNum, Model model) {
		
		List<PNotice> noticeList = pNoticeService.getNoticeList();
		pNoticeService.noticeDelete(noticeNum);
		
		model.addAttribute("faqList", noticeList);
		model.addAttribute("faqNum", noticeNum);
		model.addAttribute("title", "공지사항 삭제");
		
		return "redirect:/platform/board/noticeList";
	} */
	

	
	
	

	

}



