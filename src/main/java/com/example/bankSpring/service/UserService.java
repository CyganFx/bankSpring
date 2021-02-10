package com.example.bankSpring.service;

import com.example.bankSpring.model.User;

public interface UserService {
    User findByUsername(String username);

    User register(User user);
}
