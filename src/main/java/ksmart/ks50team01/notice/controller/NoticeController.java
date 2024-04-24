package ksmart.ks50team01.notice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.notice.dto.Notice;
import ksmart.ks50team01.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/notice")
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
		
	
	@GetMapping("/noticeList")
	public String noticeList(Model model) {
		List<Notice> noticeList = noticeService.getNoticeList();
		
		model.addAttribute("title", "공지사항 페이지");
		model.addAttribute("noticeList",noticeList);
		return "notice/noticeList";
	}
	

}
