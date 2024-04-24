package ksmart.ks50team01.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.review.dto.Review;
import ksmart.ks50team01.review.service.ReviewService;

@Controller
@RequestMapping(value="/review")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
		
	}
	
	@GetMapping("/list")
	public String getReivewList(Model model) {
		
		List<Review> reviewList = reviewService.getReivewList();
		
		model.addAttribute("title", "리뷰 페이지");
		model.addAttribute("reviewList", reviewList);
		
		return "review/list";
	}
	
}
