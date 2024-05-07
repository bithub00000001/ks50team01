package ksmart.ks50team01.platform.member.management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart.ks50team01.platform.member.management.dto.PMember;
import ksmart.ks50team01.platform.member.management.service.MemberService;
import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping(value="/platform/member")
@RequiredArgsConstructor
public class PMemberManageController {

	public final MemberService memberService;
	
	
	@GetMapping("/delMember")
	public String delMember(@RequestParam("memberId") String memberId) {
	    memberService.delMember(memberId);
	    return "redirect:/platform/member/memberManageChange";
	}
	
	
	@PostMapping("/memberModify")
	public String memberModifyProcess(@ModelAttribute PMember member) {
	    memberService.updateMember(member);
	    return "redirect:/platform/member/memberManageChange";
	}
	
	
	@GetMapping("/memberModify")
	public String memberModify(@RequestParam("memberId") String memberId, Model model) {
	    PMember memberInfo = memberService.getMemberInfoById(memberId);
	    List<PMember> memberGrade = memberService.getMemberGrade();

	    model.addAttribute("memberInfo", memberInfo);
	    model.addAttribute("memberGrade", memberGrade);
	    model.addAttribute("title", "회원수정");

	    return "platform/member/memberModify";
	}
	
	
	@GetMapping("/memberManageChange")
	public String memberManageChange(Model model) {
	    List<PMember> memberList = memberService.getMemberList();
	    List<PMember> memberGrade = memberService.getMemberGrade();

	    model.addAttribute("memberList", memberList);
	    model.addAttribute("memberGrade", memberGrade);
	    model.addAttribute("title", "회원관리");

	    return "platform/member/memberManageChange";
	}
	
	
	@GetMapping("/memberManagement")
	public String getMemberManagement(Model model) {
	    List<PMember> memberList = memberService.getMemberList();
	    List<PMember> memberGrade = memberService.getMemberGrade();

	    model.addAttribute("memberGrade", memberGrade);
	    model.addAttribute("memberList", memberList);
	    model.addAttribute("title", "회원관리");

	    return "platform/member/memberManagement";
	}
	 
	
	@PostMapping("/memberManagement")
	public String handleMemberAction(@RequestParam("memberId") String memberId,
	                                 @RequestParam("memberGrdNum") String memberGrdNum,
	                                 @RequestParam("action") String action) {
	    if ("update".equals(action)) {
	        memberService.updateMemberGrade(memberId, memberGrdNum);
	    } else if ("delete".equals(action)) {
	        memberService.delMember(memberId);
	    }
	    
	    return "redirect:/platform/member/memberManagement"; 
	}
	

}
