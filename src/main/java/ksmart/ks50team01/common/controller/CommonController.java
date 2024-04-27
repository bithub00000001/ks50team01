package ksmart.ks50team01.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("userCommonController")
public class CommonController {

    @GetMapping(value = {"", "/"})
    public String userMain(Model model) {
        model.addAttribute("title", "미정 플래너");
        return "user/main";
    }

}
