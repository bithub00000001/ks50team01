package ksmart.ks50team01.platform.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.board.dto.PReport;
import ksmart.ks50team01.platform.board.service.PReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PReportController {
	
	private final PReportService pReportService;
	
	// 게시판 신고내역 조회 페이지
	@GetMapping("reportList")
	public String reportList(Model model) {
		List<PReport> reportList = pReportService.getReportList();
		log.info("reportList:{}", reportList);
		model.addAttribute("reportList", reportList);
		model.addAttribute("title", "게시판 신고내역 조회");
		return "platform/board/reportList";
	}
}
