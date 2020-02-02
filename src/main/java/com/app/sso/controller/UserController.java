package com.app.sso.controller;

import com.app.sso.bean.User;
import com.app.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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
}
