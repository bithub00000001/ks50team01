package ksmart.ks50team01.platform.reivew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/platfom/review")
@Slf4j
public class PReviewController {
	
	/**
	 * 리뷰 신고 기록
	 */
	@GetMapping("/report/list")
	public String reportList() {
		
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
	public String reviewPlatformList() {
		
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
