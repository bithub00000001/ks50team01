package ksmart.ks50team01.user.member.login.controller;

import javax.mail.MessagingException;

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
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class MemberLoginController {

	private final LoginService loginService;
	
	@PostMapping("/findPw")
	public RedirectView findPassword(@RequestParam("id") String id, @RequestParam("email") String email, RedirectAttributes redirectAttributes, HttpServletRequest request) throws MessagingException {
	    boolean isValidUser = loginService.findPasswordByIdAndEmail(id, email);
	    if (isValidUser) {
	        String newPassword = loginService.generateAndUpdatePassword(id);
	        MailSender.sendMail(email, "임시 비밀번호 발급", "임시 비밀번호는 " + newPassword + " 입니다.");
	        redirectAttributes.addFlashAttribute("message", "이메일로 임시 비밀번호가 전송되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("message", "아이디 또는 이메일 정보가 일치하지 않습니다.");
	    }
	    String referer = request.getHeader("Referer");
	    return new RedirectView(referer);
	}
	
	@PostMapping("/bsnsJoin")
	public RedirectView bsnsJoinMember(Login login, RedirectAttributes redirectAttributes, HttpServletRequest request) {
	    try {
	        loginService.bsnsJoinMember(login);
	        redirectAttributes.addFlashAttribute("joinMessage", "회원가입에 성공하셨습니다!");
	    } catch (DuplicateKeyException e) {
	        redirectAttributes.addFlashAttribute("joinMessage", "이미 존재하는 아이디입니다.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("joinMessage", "회원가입에 실패하셨습니다.");
	    }

	    String referer = request.getHeader("Referer");
	    return new RedirectView(referer);
	}
	
    
	@PostMapping("/join")
	public RedirectView joinMember(Login login, RedirectAttributes redirectAttributes, HttpServletRequest request) {
	    try {
	        loginService.joinMember(login);
	        redirectAttributes.addFlashAttribute("joinMessage", "회원가입에 성공하셨습니다!");
	    } catch (DuplicateKeyException e) {
	        redirectAttributes.addFlashAttribute("joinMessage", "이미 존재하는 아이디입니다.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("joinMessage", "회원가입에 실패하셨습니다.");
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
	    	session.setAttribute("loginId", loginMember.getId());
	        session.setAttribute("loginName", loginMember.getName());
	        
	        // 세션에 저장된 값 확인
	        String loginId = (String) session.getAttribute("loginId");
	        String loginName = (String) session.getAttribute("loginName");
	        log.info("loginId from session: {}", loginId);
	        log.info("loginName from session: {}", loginName);
	    } else {
	        redirectAttributes.addFlashAttribute("loginError", true);
	    }
	    
	    String referer = request.getHeader("Referer");
	    return "redirect:" + referer;
	}
	
	@PostMapping("/adminLogin")
	public String adminLogin(@ModelAttribute Login login, HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
	    Login loginMember = loginService.login(login);

	    if (loginMember != null) {
	        session.setAttribute("loginId", loginMember.getId());
	        session.setAttribute("loginName", loginMember.getName());

	        // 세션에 저장된 값 확인
	        String loginId = (String) session.getAttribute("loginId");
	        String loginName = (String) session.getAttribute("loginName");

	        log.info("loginId from session: {}", loginId);
	        log.info("loginName from session: {}", loginName);

	        return "redirect:/platform/main"; // 로그인 성공 시 /platform/main으로 리다이렉트
	    } else {
	        redirectAttributes.addFlashAttribute("loginError", true);

	        String referer = request.getHeader("Referer");
	        return "redirect:" + referer; // 로그인 실패 시 현재 페이지로 리다이렉트
	    }
	}
}
