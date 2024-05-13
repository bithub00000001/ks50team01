package ksmart.ks50team01.user.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmart.ks50team01.user.review.dto.UReview;
import ksmart.ks50team01.user.review.service.UReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping(value="/user/review")
@RequiredArgsConstructor
@Slf4j
public class UReviewController {
	
	private final UReviewService uReviewService;
	
	@GetMapping("/write")
	public String reviewWrite() {
		return "user/review/reviewWrite";
	}
	
	@GetMapping("/lybalb")
	@ResponseBody
	public List<UReview> reviewJsonView(Model model) {
		
		List<UReview> uReviewList = uReviewService.getUReviewList();
		log.info("uReview: {}", uReviewList);
		System.out.println("uReviewList"+uReviewList);
		
		model.addAttribute("title", "상품 후기 목록");
		model.addAttribute("uReviewList", uReviewList);
		
		/* return "user/review/reviewList"; */
		return uReviewList;
	}
	
	@GetMapping("/list")
	public String reviewList(Model model) {
		
		List<UReview> uReviewList = uReviewService.getUReviewList();
		log.info("uReview: {}", uReviewList);
		System.out.println("uReviewList"+uReviewList);
		
		model.addAttribute("title", "상품 후기 목록");
		model.addAttribute("uReviewList", uReviewList);
		
		return "user/review/reviewList";
	}

	
	@GetMapping("/list2")
	public String reviewList2() {
		return "user/review/reviewList2";
	}
	
	@GetMapping("/list3")
	public String reviewList3() {
		return "user/review/reviewList3";
	}
	
	@GetMapping("/list4")
	public String reviewList4() {
		return "user/review/reviewList4";
	}
	@GetMapping("/list5")
	public String reviewList5() {
		return "user/review/reviewList5";
	}
	
}
