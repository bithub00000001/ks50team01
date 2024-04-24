package ksmart.ks50team01.admin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class CommonController {

    @GetMapping("/main")
    public String admin(Model model) {
        model.addAttribute("title", "관리자 페이지");
        return "admin/main";
    }
    
    @GetMapping(value={"","/"})
    public String adminLogin(Model model) {
    	
    	model.addAttribute("title", "시금치 관리자 페이지 로그인");
    	
    	return "admin/login";
    }
   
}
