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
	
	// 공지사항 목록 조회
	@GetMapping({"/",""})
	public String noticeList(Model model) {
	    List<UNotice> noticeList = uNoticeService.getNoticeList();
	    log.info("noticeList: {}", noticeList);
	    model.addAttribute("noticeList", noticeList);
		
		model.addAttribute("title", "공지사항");
		return "user/board/noticeList";
	}
	
	// 공지사항 상세 조회
	@GetMapping("/noticeDetail")
	public String noticeDetail(@RequestParam(name = "noticeNum", required = false) String noticeNum, Model model) {
		
		// 상세 페이지에 접속할 때마다 조회수 증가
		uNoticeService.increaseViewCount(noticeNum);
		
		// 게시물 정보를 가져와서 모델에 담아 상세 페이지로 전달
		UNotice noticeDetail = uNoticeService.getNoticeByNoticeNum(noticeNum);
	
	    model.addAttribute("noticeDetail", noticeDetail);
	    model.addAttribute("noticeTitle", noticeDetail.getNoticeTitle());
	    model.addAttribute("noticeContent", noticeDetail.getNoticeContent());
	    model.addAttribute("noticeContent", noticeDetail.getNoticeInqCnt());
	    model.addAttribute("title", "공지사항 상세");
	    
	    log.info("noticeNum: {}", noticeNum);
	    log.info("noticeDetail: {}", noticeDetail);
	    log.info("getNoticeByNoticeNum: {}", noticeDetail);

	    return "user/board/noticeDetail";
	}
}
