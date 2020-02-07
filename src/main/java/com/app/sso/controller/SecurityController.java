package com.app.sso.controller;

import com.app.sso.bean.User;
import com.app.sso.service.UserServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class SecurityController {

    private static final Logger log = LogManager.getLogger(SecurityController.class);

    @Autowired
    private MetadataManager metadata;

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping(value = "/saml/discovery", method = RequestMethod.GET)
    public String idpSelection(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
            log.debug("Current authentication instance from security context is null");
        else
            log.debug("Current authentication instance from security context: "
                    + this.getClass().getSimpleName());
        if (auth == null || (auth instanceof AnonymousAuthenticationToken)) {
            Set<String> idps = metadata.getIDPEntityNames();
            for (String idp : idps)
                log.info("Configured Identity Provider for SSO: " + idp);
            model.addAttribute("idps", idps);
            return "discovery.html";
        } else {
            log.warn("The current user is already logged.");
            return "redirect:/portal/home";
        }
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
