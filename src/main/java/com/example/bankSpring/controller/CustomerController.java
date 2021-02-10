package com.example.bankSpring.controller;


import com.example.bankSpring.model.CustomerAcc;
import com.example.bankSpring.model.User;
import com.example.bankSpring.service.CustomerAccService;
import com.example.bankSpring.service.UserService;
import com.example.bankSpring.threads.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Controller
public class CustomerController {
    @Autowired
    private CustomerAccService customerAccService;
    @Autowired
    private UserService userService;

    @GetMapping("/customer")
    public String viewHomePage(Model model, Principal principal, HttpServletRequest request) {
        User user = userService.findByUsername(principal.getName());
        String currency = "KZT";
        CustomerAcc customerAcc = user.getCustomerAcc();
        HttpSession session = request.getSession();
        Double dollar = (Double) session.getAttribute("dollar");

        if (dollar != null) {
            if (customerAcc.getBalance() != 0) {
                double balance = customerAcc.getBalance() / dollar;
                balance = Math.round(balance * 100) / 100.0;
                customerAcc.setBalance(balance);
                currency = "USD";
            }
        }

        model.addAttribute("currency", currency);
        model.addAttribute("Customer", customerAcc);
        return "userPage";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("amount", "");

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPost(@ModelAttribute("amount") String amount, Principal principal, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Double dollar = (Double) session.getAttribute("dollar");
        if (dollar != null) {
            customerAccService.deposit(Double.parseDouble(amount) * dollar, principal);
        } else {
            customerAccService.deposit(Double.parseDouble(amount), principal);
        }
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

    @GetMapping("/convertCurrency")
    public void convertCurrency(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("dollar", 418.81);
        response.sendRedirect("/customer");
    }

    @GetMapping("/convertBack")
    public void convertBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("dollar");
        response.sendRedirect("/customer");
    }

    @GetMapping("/otherDeposit")
    public String showDepositPage(Model model) {
        model.addAttribute("amount", "");
        model.addAttribute("cardNumber", "");

        return "otherDeposit";
    }

    @PostMapping("/otherDeposit")
    public String addToOtherDeposit(@ModelAttribute("amount") String amount, @ModelAttribute("cardNumber") String cardNumber, Principal principal, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Double dollar = (Double) session.getAttribute("dollar");
        if (dollar != null) {
            customerAccService.addToOtherDeposit(Double.parseDouble(amount) * dollar, cardNumber, principal);
        } else {
            customerAccService.addToOtherDeposit(Double.parseDouble(amount), cardNumber, principal);
        }
        return "redirect:/info";
    }

    @GetMapping("/complaint")
    public String showComplaintPage(Model model) {
        model.addAttribute("message", "");
        return "complaints";
    }

    @PostMapping("/complaint")
    public String processComplaint(@ModelAttribute("message") String message, Model model) throws InterruptedException, IOException, ClassNotFoundException {
        Client clientSocket = new Client();
        String responseMessage = clientSocket.processComplaintAndGetResponse(message);
        model.addAttribute("message", responseMessage);
        return "complaints";
    }
}
