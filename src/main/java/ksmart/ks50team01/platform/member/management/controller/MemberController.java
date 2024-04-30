package ksmart.ks50team01.platform.member.management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmart.ks50team01.platform.member.management.dto.Member;
import ksmart.ks50team01.platform.member.management.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/platform/member")
@RequiredArgsConstructor
public class MemberController {

	public final MemberService memberService;
	
	
	@GetMapping("/memberManagement")
	public String getMemberManagement(Model model) {
		
		List<Member> memberList = memberService.getMemberList();
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("title", "회원관리");
	
		return "platform/member/memberManagement";
	}
}
