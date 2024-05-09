package ksmart.ks50team01.user.member.mypage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String memberModifyProcess(@ModelAttribute Mypage mypage, RedirectAttributes redirectAttributes, HttpSession session) {
        int updatedCount = mypageService.updateMember(mypage);
        String loginId = (String) session.getAttribute("loginId");

        if (updatedCount > 0) {
            redirectAttributes.addFlashAttribute("successMessage", "회원 정보가 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "회원 정보 수정에 실패했습니다.");
        }

        redirectAttributes.addAttribute("loginId", loginId);
        return "redirect:/mypage/mypageManage";
    }
    
    @GetMapping("/delMember")
    public String delMember(@RequestParam("memberId") String memberId, HttpSession session) {
        mypageService.delMember(memberId);
        log.info("memberId: {}", memberId);
        session.invalidate(); // 세션 무효화
        return "redirect:/trip";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/trip";
    }

    @GetMapping("/mypage")
    public String mypage(Model model) {
        model.addAttribute("title", "마이페이지");
        return "user/mypage/main";
    }

    @GetMapping("/mypageManage")
    public String memberModify(@SessionAttribute("loginId") String loginId, Model model) {
        Mypage memberInfo = mypageService.getMemberInfoById(loginId);
        List<Mypage> memberGrade = mypageService.getMemberGrade();

        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("memberGrade", memberGrade);
        model.addAttribute("title", "회원정보수정");

        return "user/mypage/mypageManage";
    }
}
