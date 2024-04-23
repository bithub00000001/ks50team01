package ksmart.ks50team01.admin.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@GetMapping("/managecate")
	public String cateCRUD(Model model) {
		
		model.addAttribute("title", "카테고리관리");
		
		return "admin/manage/managecate";
	}

	@GetMapping("/notice")
	public String noticeCRUD(Model model) {
		
		model.addAttribute("title", "공지사항관리");
		
		return "admin/manage/notice";
	}
	
	@GetMapping("/faq")
	public String faqCRUD(Model model) {
		
		model.addAttribute("title", "자주찾는 질문 관리");
		
		return "admin/manage/faq";
	}
	
	@GetMapping("/qna")
	public String qnaCRUD(Model model) {
		
		model.addAttribute("title", "1:1문의 관리");
		
		return "admin/manage/qna";
	}
	
	@GetMapping("/community")
	public String communityCRUD(Model model) {
		
		model.addAttribute("title", "게시판 관리");
		
		return "admin/manage/community";
	}
	@GetMapping("communityRpt")
	public String communityRptCRUD(Model model) {
		
		model.addAttribute("title", "게시판 신고 관리");
		
		return "admin/manage/communityRpt";
	}
}
