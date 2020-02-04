package com.app.sso.controller;

import com.app.sso.bean.User;
import com.app.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    public List<User> getUsers() {
        return null;
    }

    public User getProfile() {
        return null;
    }

    public String enableUser(Long id) {
        return null;
    }


    @GetMapping({"/admin/home"})
    public String admin(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "admin/home.html";
    }

    @GetMapping({"/portal/home"})
    public String portal(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "portal/home.html";
    }

    @PostMapping("/doRegistration")
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = service.findByUsername(user.getUsername());
        if (userExists.isPresent()) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login.html");
        } else {
            User refreshUser = service.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", refreshUser);
            modelAndView.setViewName("login.html");
        }
        return modelAndView;
    }
}
