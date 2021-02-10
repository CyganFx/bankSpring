package com.example.bankSpring.controller;


import com.example.bankSpring.model.CustomerAcc;
import com.example.bankSpring.model.TransactionInfo;
import com.example.bankSpring.model.User;
import com.example.bankSpring.service.TransactionInfoService;
import com.example.bankSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class TransactionInfoController {
    @Autowired
    private TransactionInfoService transactionInfoService;
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String viewHomePage(Model model, Principal principal, HttpServletRequest request) {
        List<TransactionInfo> transactionInfoList = transactionInfoService.findTransactionList(principal.getName());
        HttpSession session = request.getSession();
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        Double dollar = (Double) session.getAttribute("dollar");
        double balance;
        double operationAmount;

        if (dollar != null) {
            for (TransactionInfo transaction : transactionInfoList) {
                balance = transaction.getBalance() / dollar;
                balance = Math.round(balance * 100) / 100.0;
                transaction.setBalance(balance);
                operationAmount = transaction.getOperationAmount() / dollar;
                operationAmount = Math.round(operationAmount * 100) / 100.0;
                transaction.setOperationAmount(operationAmount);
            }
        }
        model.addAttribute("listTransactions", transactionInfoList);
        model.addAttribute("customerAcc", customerAcc);
        return "main";
    }

    @RequestMapping("main")
    public String main() {
        return "main";
    }
}
