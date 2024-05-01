package ksmart.ks50team01.user.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/review")
@RequiredArgsConstructor
public class UReportController {
	
	@GetMapping("/report")
	public String reviewWrite() {
		return "user/review/reviewReport";
	}
	

}
