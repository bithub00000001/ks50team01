package ksmart.ks50team01.user.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/user/review")
public class URReportController {
	
	@GetMapping("/report")
	public String reviewWrite() {
		return "user/review/reviewReport";
	}
	

}
