package com.app.sso.controller;

import com.app.sso.bean.User;
import com.app.sso.service.UserService;
import com.app.sso.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping({"/admin/users"})
    public String getUsers(Model model) {
        List<User> users = service.listAll();
        UserWrapper userWrapper = new UserWrapper();
        userWrapper.addUsers(users);
        model.addAttribute("forms", userWrapper);
        return "/admin/users.html";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String currentUserName(Model model) {
        Optional<User> authenticatedUser = service.getAuthenticatedUser();
        if(authenticatedUser.isPresent()) {
            model.addAttribute("user", authenticatedUser.get());
        }else {
            model.addAttribute("error", "Failed to load authenticated user");
        }
        return "profile.html";
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
        String url = "portal/home.html";
        model.addAttribute("url", url);
        return url;
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
