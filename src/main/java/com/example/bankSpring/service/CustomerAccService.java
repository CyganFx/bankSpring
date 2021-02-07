package com.example.bankSpring.service;


import com.example.bankSpring.model.CustomerAcc;

import java.security.Principal;
import java.util.List;

public interface CustomerAccService {
    List<CustomerAcc> getAllAccounts();
    CustomerAcc findCustomer(String username);
    void deposit(double amount, Principal principal);
    void withdraw(double amount, Principal principal);
}
