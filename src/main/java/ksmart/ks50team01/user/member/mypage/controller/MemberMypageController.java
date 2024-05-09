package ksmart.ks50team01.user.member.mypage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ksmart.ks50team01.user.member.mypage.dto.Mypage;
import ksmart.ks50team01.user.member.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MemberMypageController {

    public final MypageService mypageService;
    

    @PostMapping("/mypageManage")
    @ResponseBody
    public String memberModifyProcess(@ModelAttribute Mypage mypage) {
        int updatedCount = mypageService.updateMember(mypage);

        if (updatedCount > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
    
    @PostMapping("/delMember")
    @ResponseBody
    public String delMember(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        mypageService.delMember(loginId);
        session.invalidate();
        return "success";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/trip";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        model.addAttribute("loginId", loginId);
        return "user/mypage/main";
    }

    @GetMapping("/mypageManage")
    @ResponseBody
    public Mypage memberModify(@SessionAttribute("loginId") String loginId) {
        Mypage memberInfo = mypageService.getMemberInfoById(loginId);
        return memberInfo;
    }
}
