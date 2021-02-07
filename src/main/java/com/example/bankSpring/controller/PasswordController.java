package com.example.bankSpring.controller;


import com.example.bankSpring.model.User;
import com.example.bankSpring.security.UserDetails;
import com.example.bankSpring.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PasswordController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/changePassword")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("pageTitle", "Change Password");
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String processChangePassword(HttpServletRequest request, HttpServletResponse response,
                                        Model model, RedirectAttributes ra,
                                        @AuthenticationPrincipal Authentication authentication) throws ServletException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        if (oldPassword.equals(newPassword)) {
            model.addAttribute("message", "Your newPassword must not be the same as the oldest password that you used!");
            return "changePassword";
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("message", "Your old password is incorrect, please try again...");
            return "changePassword";
        } else {
            userService.changePassword(user, newPassword);
            request.logout();
            ra.addFlashAttribute("message", "You have changed your password successfully. "
                    + "Please login again.");

        }
        return "redirect:/login";
    }

    @RequestMapping("changePassword")
    public String changePassword() {
        return "changePassword";
    }
}
