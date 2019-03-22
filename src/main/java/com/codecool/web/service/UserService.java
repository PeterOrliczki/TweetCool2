package com.codecool.web.service;

import com.codecool.web.model.User;

import java.util.ArrayList;
import java.util.List;

public final class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User("admin@admin", "admin"));
        users.add(new User("user@user", "user"));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
