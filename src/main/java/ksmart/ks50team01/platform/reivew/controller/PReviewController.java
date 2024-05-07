package ksmart.ks50team01.platform.reivew.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.reivew.dto.PReview;
import ksmart.ks50team01.platform.reivew.service.PReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/platform/review")
@RequiredArgsConstructor
@Slf4j
public class PReviewController {

	private final PReviewService pReviewService;
	
	/**
	 * 리뷰 신고 목록
	 */
	@GetMapping("/report/list")
	public String reportList(Model model) {
		
		return "platform/review/reportList";
	}
	
	/**
	 * 리뷰 신고 승인
	 * @return
	 */
	@GetMapping("/report/approve")
	public String reporApprove() {
		
		return "platform/review/reportApprove";
	}
	
	/**
	 * 리뷰 전체 목록
	 * @return
	 */
	@GetMapping("/list")
	public String reviewPlatformList(Model model) {
		
		List<PReview> pReviewList = pReviewService.getPReviewList();
		
		model.addAttribute("title", "상품리뷰전체목록");
		model.addAttribute("pReviewList", pReviewList);
		
		return "platform/review/PreviewList";
	}
	
	/**
	 * 리뷰 공개 여부 수정
	 * @return
	 */
	@GetMapping("/public")
	public String reviewPublic() {
		
		return "platform/review/reviewPublic";
	}
	
	/**
	 * 리뷰 신고 누적 개수
	 * @return
	 */
	@GetMapping("/total")
	public String reviewTotal() {
		
		return "platform/review/reportTotal";
	}
	
	/**
	 * 답글 목록
	 * @return
	 */
	@GetMapping("/comment")
	public String commentList() {
		
		return "platform/review/commentList";
	}
	

}
