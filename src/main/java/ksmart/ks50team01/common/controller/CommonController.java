package ksmart.ks50team01.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("userCommonController")
public class CommonController {

    @GetMapping(value = {"", "/"})
    public String userMain(Model model) {
        model.addAttribute("title", "미정 플래너");
        return "index";
    }

    @GetMapping("/platform/refer")
    public String platformRefer(Model model) {
        model.addAttribute("title", "플랫폼 페이지 레퍼런스");
        return "platform/referPage1";
    }

    @GetMapping("/user/reference")
    public String userRefer(Model model) {
        model.addAttribute("title", "유저 페이지 레퍼런스");
        return "user/referPage2";
    }
}
