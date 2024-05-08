package ksmart.ks50team01.platform.reivew.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.reivew.dto.PPublic;
import ksmart.ks50team01.platform.reivew.dto.PReivewComment;
import ksmart.ks50team01.platform.reivew.dto.PReview;
import ksmart.ks50team01.platform.reivew.dto.PReviewReact;
import ksmart.ks50team01.platform.reivew.dto.PReviewReport;
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
		
		List<PReviewReport> pReviewReport = pReviewService.getPReviewReports();
		
		model.addAttribute("title", "상품리뷰전체목록");
		model.addAttribute("pReviewReport", pReviewReport);
		
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
	 * 05.08 수정
	 * 리뷰 전체 목록 - 회원목록조회
	 * @return
	 */
	@GetMapping("/list")
	public String getPReviewList(Model model) {
		
		List<PReview> pReviewList = pReviewService.getPReviewList();
		log.info("pReviewList: {}", pReviewList);
		System.out.println("pReviewList : " + pReviewList);
		
		model.addAttribute("title", "상품리뷰전체목록");
		model.addAttribute("pReviewList", pReviewList);
		
		return "platform/review/PreviewList";
	}
	
	/**
	 * 리뷰 수정 - 회원 수정
	 * 05.08 작성
	 */
	@PostMapping("/public")
	public String modifyPReview(PReview review) {
		log.info("리뷰 수정 : {}", review);
		
		pReviewService.modifyPReview(review);
		
		return "redirect:/review/PreviewList";
	}
	
	/**
	 * 05.08 수정
	 * 리뷰 공개 여부 수정(수정페이지) - 회원 수정 화면
	 * @return
	 */
	@GetMapping("/public")
	public String modifyPReview(@RequestParam(value="reviewCode") String reviewCode, Model model) {
		log.info("수정화면 reviewCode : {}", reviewCode);
		PReview reviewInfo = pReviewService.getPReviewInfoById(reviewCode);
		List<PPublic> pPublicList = pReviewService.getPPublicList();
		
		model.addAttribute("title", "리뷰수정");
		model.addAttribute("reviewInfo", reviewInfo);
		model.addAttribute("pPublicList", pPublicList);
		
		
		return "platform/review/reviewPublic";
	}
	
	
	/**
	 * 리뷰 좋아요 싫어요 기록 목록
	 */
	@GetMapping("/react")
	public String reviewReact(Model model) {
		
		List<PReviewReact> pReviewReacts = pReviewService.getPReviewReact();
		
		model.addAttribute("title", "좋아요 싫어요 기록 목록");
		model.addAttribute("pReviewReacts", pReviewReacts);
		
		return "platform/review/reviewReact";
	}
	
	
	/**
	 * 리뷰 신고 누적 개수
	 * @return
	 */
	@GetMapping("/total/list")
	public String reviewTotal() {
		
		return "platform/review/reportTotal";
	}
	
	/**
	 * 답글 공개 여부 수정
	 * @return
	 */
	@GetMapping("/comment/public")
	public String commentPublic() {
		
		return "platform/review/commentPublic";
	}
	
	/**
	 * 답글 목록
	 * @return
	 */
	@GetMapping("/comment/list")
	public String commentList(Model model) {
		
		List<PReivewComment> pReivewComment = pReviewService.getPReivewComment();
		
		model.addAttribute("title", "댓글 목록 리스트");
		model.addAttribute("pReivewComment", pReivewComment);
		
		return "platform/review/commentList";
	}
	

}
