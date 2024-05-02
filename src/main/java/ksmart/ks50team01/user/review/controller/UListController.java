package ksmart.ks50team01.user.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value="/user/review")
public class UListController {
	
	@GetMapping("/list")
	public String reviewList() {
		return "user/review/reviewList";
	}
	
}
