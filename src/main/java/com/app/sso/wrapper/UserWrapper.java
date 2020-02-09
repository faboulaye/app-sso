package com.app.sso.wrapper;

import com.app.sso.bean.User;

import java.util.ArrayList;
import java.util.List;

public class UserWrapper {

    private List<User> users;

    public void addUsers(List<User> users) {
        this.getUsers().addAll(users);
    }

    public List<User> getUsers() {
        if(users == null) {
            this.users = new ArrayList<>();
        }
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
