package ksmart.ks50team01.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.member.dto.Member;
import ksmart.ks50team01.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/memberList")
	public String getMemeberList(Model model) {
		
		List<Member> memberList = memberService.getmemberList();
		model.addAttribute("title", "회원조회");
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}
}
