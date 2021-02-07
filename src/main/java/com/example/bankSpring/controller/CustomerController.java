package com.example.bankSpring.controller;


import com.example.bankSpring.model.CustomerAcc;
import com.example.bankSpring.model.User;
import com.example.bankSpring.service.CustomerAccService;
import com.example.bankSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class CustomerController {
    @Autowired
    private CustomerAccService customerAccService;
    @Autowired
    private UserService userService;

    @GetMapping("/customer")
    public String viewHomePage(Model model, Principal principal) {
//        CustomerAcc customerAcc = customerAccService.findCustomer(principal.getName());
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        model.addAttribute("Customer", customerAcc);
        return "userPage";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("amount", "");

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPost(@ModelAttribute("amount") String amount, Principal principal) {
        customerAccService.deposit(Double.parseDouble(amount), principal);
        return "redirect:/info";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
        model.addAttribute("amount", "");
        return "withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPOST(@ModelAttribute("amount") String amount, Principal principal) {
        customerAccService.withdraw(Double.parseDouble(amount), principal);

        return "redirect:/info";
    }
}
