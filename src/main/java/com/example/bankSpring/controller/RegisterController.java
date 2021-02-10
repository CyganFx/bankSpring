package com.example.bankSpring.controller;

import com.example.bankSpring.model.User;
import com.example.bankSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model, Principal principal) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("message", "such user already exist");
            return "registration";
        }
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        userService.register(user1);
        return "index";
    }
}
