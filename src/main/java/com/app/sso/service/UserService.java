package com.app.sso.service;

import com.app.sso.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService, SAMLUserDetailsService {

    String SERVICE_NAME = "userService";

    User save(User user);

    void delete(User user);

    List<User> listAll();

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> getAuthenticatedUser();
}
