package ksmart.ks50team01.platform.member.management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/memberGrade")
	public String getMemberGrade(Model model) {
		
		return "platform/member/memberGrade";
	}
	
	
	@PostMapping("/memberGrade2")
	public String modifyMember(@RequestParam(value="memberId") String memberId
							  ,Model model) {

		PMember memberInfo = memberService.getMemberInfoById(memberId);
		List<PMember> memberList = memberService.getMemberList();
		
		model.addAttribute("title", "회원수정");
		model.addAttribute("memberInfo", memberInfo);
		model.addAttribute("memberList", memberList);
		

		
		return "platform/member/memberGrade2";
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
