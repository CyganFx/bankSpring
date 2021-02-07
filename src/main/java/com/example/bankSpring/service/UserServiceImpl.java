package com.example.bankSpring.service;


import com.example.bankSpring.model.User;
import com.example.bankSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public void changePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
