package ksmart.ks50team01.user.board.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
	public String noticeList(@RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage
							,Model model) {
		//List<UNotice> noticeList = uNoticeService.getNoticeList();
		
		Map<String, Object> resultMap = uNoticeService.getNoticeList(currentPage);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> noticeList = (List<Map<String, Object>>) resultMap.get("noticeList");
		int lastPage = (int) resultMap.get("lastPage");
		int startPageNum = (int) resultMap.get("startPageNum");
		int lastPageNum = (int) resultMap.get("lastPageNum");
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
	    
	    model.addAttribute("currentDate", LocalDate.now()); // 현재 날짜 추가
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
		UNotice noticeDetail = uNoticeService.getNoticeDetail(noticeNum);
	
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
