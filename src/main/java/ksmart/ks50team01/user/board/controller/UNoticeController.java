package ksmart.ks50team01.user.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.user.board.dto.UNotice;
import ksmart.ks50team01.user.board.service.UNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@Slf4j
public class UNoticeController {
	
	private final UNoticeService uNoticeService;
	
	// 공지사항 조회 페이지
	@GetMapping({"/",""})
	public String noticeList(Model model) {
	    List<UNotice> noticeList = uNoticeService.getNoticeList();
	    log.info("noticeList: {}", noticeList);
	    model.addAttribute("noticeList", noticeList);
		
		model.addAttribute("title", "공지사항");
		return "user/board/noticeList";
	}
	
	// 특정 공지사항 상세 조회
	@GetMapping("/noticeDetail")
	public String noticeDetail(@RequestParam(name = "noticeNum", required = false) String noticeNum, Model model) {
		UNotice noticeDetail = uNoticeService.getNoticeByNoticeNum(noticeNum);
	
	    model.addAttribute("noticeDetail", noticeDetail);
	    model.addAttribute("noticeTitle", noticeDetail.getNoticeTitle());
	    model.addAttribute("noticeContent", noticeDetail.getNoticeContent());
	    model.addAttribute("title", "공지사항 상세");
	    
	    log.info("noticeNum: {}", noticeNum);
	    log.info("getNoticeByNoticeNum: {}", noticeDetail);

	    return "user/board/noticeDetail";
	}
}
