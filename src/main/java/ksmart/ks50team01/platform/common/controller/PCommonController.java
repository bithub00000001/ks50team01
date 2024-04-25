package ksmart.ks50team01.platform.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/platform")
public class PCommonController {

    @GetMapping("/main")
    public String platform(Model model) {
        model.addAttribute("title", "관리자 페이지");
        return "platform/main";
    }
    
    @GetMapping(value={"","/"})
    public String platformLogin(Model model) {
    	
    	model.addAttribute("title", "시금치 관리자 페이지 로그인");
    	
    	return "platform/common/login";
    }
   
}
