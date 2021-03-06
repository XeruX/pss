package utp.pss.patryklewandowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index.html";
    }

    @PostMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }
}
