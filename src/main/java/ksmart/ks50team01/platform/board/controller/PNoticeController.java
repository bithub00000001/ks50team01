package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	    pNoticeService.noticeWrite(pNotice);
	    return "redirect:/platform/board/noticeList";
	}
	
	// 공지사항 작성 페이지
	@GetMapping("/noticeWrite")
	public String noticeWrite(Model model) {
		model.addAttribute("title", "공지사항 작성");
		return "platform/board/noticeWrite";
	}
	
	// 공지사항 수정 POST 요청
		@PostMapping("/noticeModify")
		public String noticeModify(PNotice pNotice, Model model) {
			log.info("noticeModify: {}", pNotice);
			pNoticeService.noticeModify(pNotice);
			model.addAttribute("title", "공지사항 수정 페이지");
			return "redirect:/platform/board/noticeList";
		}
	
	// 공지사항 수정 페이지
	@GetMapping("/noticeModify")
	public String noticeModify(@RequestParam(value = "noticeNum") String noticeNum, Model model) {
		PNotice pNotice = pNoticeService.getNoticeByNum(noticeNum);
		model.addAttribute("pNotice", pNotice);
		model.addAttribute("title", "공지사항 수정");
		return "platform/board/noticeModify";
	}
	

	
	
	

	

}



