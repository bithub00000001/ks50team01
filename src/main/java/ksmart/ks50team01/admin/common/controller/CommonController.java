package ksmart.ks50team01.admin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("title", "관리자 페이지");
        return "admin/main";
    }
}
