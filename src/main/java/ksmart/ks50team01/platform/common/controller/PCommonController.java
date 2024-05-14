package ksmart.ks50team01.platform.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/platform")
public class PCommonController {

    @GetMapping("/main")
    public String platform(Model model, HttpSession session) {
        String loginLevel = (String) session.getAttribute("loginLevel");
        
        if (loginLevel == null || !loginLevel.equals("uln_001")) {
            return "redirect:/platform"; // 로그인되어 있지 않거나 권한이 없는 경우 / 경로로 리다이렉트
        }
        
        model.addAttribute("title", "관리자 페이지");
        return "platform/main";
    }

    @GetMapping(value={"","/"})
    public String platformLogin(Model model) {
        model.addAttribute("title", "시금치 관리자 페이지 로그인");
        return "platform/common/login";
    }
}