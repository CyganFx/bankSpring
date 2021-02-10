package com.example.bankSpring.service;

import java.security.Principal;


public interface CustomerAccService {
    void deposit(double amount, Principal principal);

    void withdraw(double amount, Principal principal);

    void addToOtherDeposit(double amount, String cardNumber, Principal principal);
}
