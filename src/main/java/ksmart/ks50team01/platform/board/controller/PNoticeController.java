package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PNotice;
import ksmart.ks50team01.platform.board.service.PNoticeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/platform/board")
@Slf4j
public class PNoticeController {
	
	private final PNoticeService pNoticeService;
	
	public PNoticeController(PNoticeService pNoticeService) {
		this.pNoticeService = pNoticeService;
	}
	
	@GetMapping("/noticeWrite")
	public String noticeWrite(Model model) {
		
		model.addAttribute("title", "공지사항 작성");
		return "platform/board/noticeWrite";
		
	}
	
	@GetMapping("/noticeList")
	public String noticeList(Model model) {
	    List<PNotice> noticeList = pNoticeService.getNoticeList();
	    
	    model.addAttribute("noticeList", noticeList);
		model.addAttribute("title", "공지사항 조회");
		
		return "platform/board/noticeList";
		
	}
	
	@GetMapping("/noticeModify")
	public String noticeModify(Model model) {
		
		model.addAttribute("title", "공지사항 수정");
		
		return "platform/board/noticeModify";
	}
	
	

	

}



