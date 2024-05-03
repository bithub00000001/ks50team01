package ksmart.ks50team01.user.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value="/user/review")
public class UReviewContentController {
	
	@GetMapping("/write")
	public String reviewWrite() {
		return "user/review/reviewWrite";
	}
	
	@GetMapping("/comment")
	public String reviewComment() {
		return "user/review/commentWrite";
	}
	
}
