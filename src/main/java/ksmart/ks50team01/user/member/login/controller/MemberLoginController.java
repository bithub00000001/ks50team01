package ksmart.ks50team01.user.member.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.member.login.service.LoginService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class MemberLoginController {

	private final LoginService loginService;
    
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/trip";
    }
	
	@PostMapping("/login")
	public String login(@ModelAttribute Login login, HttpSession session, RedirectAttributes redirectAttributes) {
	    Login loginMember = loginService.login(login);
	    if (loginMember != null) {
	        session.setAttribute("loginId", loginMember.getId());		// 세션에 회원 아이디 저장	
	        session.setAttribute("loginName", loginMember.getName());	// 세션에 회원 이름 저장
		} else {
	        redirectAttributes.addFlashAttribute("loginError", true);
		}
		return "redirect:/trip";
	}
}
