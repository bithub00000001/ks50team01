package ksmart.ks50team01.platform.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.platform.board.dto.PReport;
import ksmart.ks50team01.platform.board.service.PReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping(value = "/platform/board")
@RequiredArgsConstructor
@Slf4j
public class PReportController {
	
	private final PReportService pReportService;
	
	// 게시판 신고내역 조회 페이지
	@GetMapping("reportList")
	public String reportList(@RequestParam(value = "category", required = false) String category, Model model) {
		List<PReport> reportList;
		
	    if (category != null) {
	    	reportList = pReportService.getReportListByCategory(category);
	    } else {
	    	reportList = pReportService.getReportList();
	    }
		
		log.info("reportList:{}", reportList);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("selectedCategory", category);
		model.addAttribute("title", "게시판 신고내역 조회");
		
		return "platform/board/reportList";
	}
	
	
	/**
	 * 신고 승인
	 * @param reportNum
	 * @return
	 */
    @PostMapping("/approveReport")
    @ResponseBody
    public Map<String, Object> approveReport(@RequestParam("reportNum") String reportNum) {
        log.info("승인할 신고 번호: {}", reportNum);

        Map<String, Object> response = new HashMap<>();
        try {
        	pReportService.approveReport(reportNum);
            response.put("success", true);
            response.put("message", "신고내역이 승인되었습니다.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "승인 실패");
        }

        return response;
    }
}
