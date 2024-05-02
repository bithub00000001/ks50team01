package ksmart.ks50team01.platform.member.management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.member.management.dto.Member;
import ksmart.ks50team01.platform.member.management.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/platform/member")
@RequiredArgsConstructor
public class MemberController {

	public final MemberService memberService;
	
	@GetMapping("/memberGrade")
	public String getMemberGrade(Model model) {
		
		return "platform/member/memberGrade";
	}
	
	
	@PostMapping("/memberGrade2")
	public String modifyMember(@RequestParam(value="memberId") String memberId
							  ,Model model) {

		Member memberInfo = memberService.getMemberInfoById(memberId);
		List<Member> memberList = memberService.getMemberList();
		
		model.addAttribute("title", "회원수정");
		model.addAttribute("memberInfo", memberInfo);
		model.addAttribute("memberList", memberList);
		

		
		return "platform/member/memberGrade2";
	}
	
	
	@GetMapping("/memberManagement")
	public String getMemberManagement(Model model) {
		
		List<Member> memberList = memberService.getMemberList();
		Member memberCount = memberService.memberCount();
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("title", "회원관리");
		model.addAttribute("memberCount", memberCount);
	
		return "platform/member/memberManagement";
	}
}
