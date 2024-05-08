package ksmart.ks50team01.user.member.login.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.common.mail.MailSender;
import ksmart.ks50team01.user.member.login.dto.Login;
import ksmart.ks50team01.user.member.login.service.LoginService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class MemberLoginController {

	private final LoginService loginService;
    
	@PostMapping("/join")
	public RedirectView joinMember(Login login, RedirectAttributes redirectAttributes, HttpServletRequest request) {
	    try {
	        loginService.joinMember(login);
	        
	        // 메일 발송 코드 호출
	        String recipient = login.getEmail();
	        String subject = "환영합니다! 회원가입을 축하드립니다.";
	        String body = "회원님의 가입을 진심으로 환영합니다. 저희 서비스를 많이 이용해주세요.";
	        MailSender.sendMail(recipient, subject, body);
	        
	        redirectAttributes.addFlashAttribute("joinMessage", "회원가입에 성공하셨습니다!");
	    } catch (DuplicateKeyException e) {
	        redirectAttributes.addFlashAttribute("joinMessage", "이미 존재하는 아이디입니다.");
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 추적을 위해 추가
	    }

	    String referer = request.getHeader("Referer");
	    return new RedirectView(referer);
	}
	
	@PostMapping("/findId")
	public String findId(@RequestParam("name") String name, @RequestParam("phone") String phone, Model model, RedirectAttributes redirectAttributes) {
	    String memberId = loginService.findId(name, phone);
	    if (memberId != null) {
	        redirectAttributes.addFlashAttribute("memberId", memberId);
	    } else {
	        redirectAttributes.addFlashAttribute("message", "해당 정보로 등록된 회원이 없습니다.");
	    }
	    return "redirect:/trip";
	}
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/trip";
    }
	
	@PostMapping("/login")
	public String login(@ModelAttribute Login login, HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
	    Login loginMember = loginService.login(login);

	    if (loginMember != null) {
	        session.setAttribute("loginId", loginMember.getId()); // 세션에 회원 아이디 저장
	        session.setAttribute("loginName", loginMember.getName()); // 세션에 회원 이름 저장
	    } else {
	        redirectAttributes.addFlashAttribute("loginError", true);
	    }

	    // 현재 페이지로 리다이렉트
	    String referer = request.getHeader("Referer");
	    return "redirect:" + referer;
	}
}
