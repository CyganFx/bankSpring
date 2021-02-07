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

import java.security.Principal;
import java.util.List;

@Controller
public class TransactionInfoController {
    @Autowired
    private TransactionInfoService transactionInfoService;
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String viewHomePage(Model model, Principal principal) {
        List<TransactionInfo> transactionInfoList = transactionInfoService.findTransactionList(principal.getName());
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        model.addAttribute("listTransactions", transactionInfoList);
        model.addAttribute("customerAcc", customerAcc);
        return "main";
    }

    @RequestMapping("main")
    public String main(){
        return "main";
    }
}
