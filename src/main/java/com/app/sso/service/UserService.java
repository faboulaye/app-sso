package com.app.sso.service;

import com.app.sso.bean.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String SERVICE_NAME = "userService";

    User save(User user);

    void delete(User user);

    List<User> listAll();

    Optional<User> findById(Long id);
}
