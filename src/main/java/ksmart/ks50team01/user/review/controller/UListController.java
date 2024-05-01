package ksmart.ks50team01.user.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value="/review")
@RequiredArgsConstructor
public class UListController {
	
	@GetMapping("/list")
	public String reviewList() {
		return "user/review/reviewList";
	}
	
}
