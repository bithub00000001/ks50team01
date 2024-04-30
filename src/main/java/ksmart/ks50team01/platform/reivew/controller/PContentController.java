package ksmart.ks50team01.platform.reivew.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value="/platform")
@RequiredArgsConstructor
public class PContentController {
	
	@GetMapping("/review")
	public String getMethodName(@RequestParam String param) {
		
		
		
		return "platform/member/memberManagement";
	}
	

}
