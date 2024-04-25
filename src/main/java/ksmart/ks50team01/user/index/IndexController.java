package ksmart.ks50team01.user.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/trip","/trip/"})
    public String index(Model model) {
        model.addAttribute("title", "인덱스");

        return "user/trip/index";
    }
}
