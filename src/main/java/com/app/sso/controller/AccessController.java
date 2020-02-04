package com.app.sso.controller;

import com.app.sso.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccessController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/doLogin")
    public String doSuccessLogin(Model model) {
        System.out.println(model);
        return "";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }
}
