package blog.smongs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private String apiKey = "d8de0361754a5a65283d445a79deeea6";
    private String url = "https://javascriptkr-silver-enigma-4v4j4wjr65gh9jv.github.dev/auth/kakao/callback";
    
    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(Model model){
        model.addAttribute("REST_API_KEY", apiKey);
        model.addAttribute("REDIRECT_URI", url);
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }
}
