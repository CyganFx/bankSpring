package com.example.bankSpring.service;


import com.example.bankSpring.model.CustomerAcc;
import com.example.bankSpring.model.TransactionInfo;
import com.example.bankSpring.model.User;
import com.example.bankSpring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;


@Service
public class CustomerAccServiceImpl implements CustomerAccService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionInfoService transactionInfoService;

    @Override
    public void deposit(double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        String description = "Deposit to the account";
        if (amount > 100_000) {
            amount -= amount * 0.01;
            description = "Deposit to the account (commission 1%)";
        }
        customerAcc.setBalance(customerAcc.getBalance() + amount);
        customerRepository.save(customerAcc);
        Date date = new Date();
        TransactionInfo transactionInfo = new TransactionInfo(date, description, "success", amount, customerAcc, customerAcc.getBalance());
        transactionInfoService.saveDepositTransactionInfo(transactionInfo);
    }

    @Override
    public void withdraw(double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        customerAcc.setBalance(customerAcc.getBalance() - amount);
        customerRepository.save(customerAcc);
        Date date = new Date();
        TransactionInfo transactionInfo = new TransactionInfo(date, "Withdraw from account", "success", amount, customerAcc, customerAcc.getBalance());
        transactionInfoService.saveDepositTransactionInfo(transactionInfo);
    }

    @Override
    public void addToOtherDeposit(double amount, String cardNumber, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        CustomerAcc currentCustomer = user.getCustomerAcc();
        currentCustomer.setBalance(currentCustomer.getBalance() - (amount + amount * 0.01));

        CustomerAcc otherCustomer = customerRepository.getCustomerByCardNumber(cardNumber);
        System.out.println("Balance " + otherCustomer.getBalance());
        otherCustomer.setBalance(otherCustomer.getBalance() + amount);
        customerRepository.save(currentCustomer);
        customerRepository.save(otherCustomer);

        Date date = new Date();
        TransactionInfo transactionInfo = new TransactionInfo(date, "Deposit to another account (commission 1%)", "success", amount, currentCustomer, currentCustomer.getBalance());
        TransactionInfo transactionInfo2 = new TransactionInfo(date, "Accept from other account", "success", amount, otherCustomer, otherCustomer.getBalance());

        transactionInfoService.saveDepositTransactionInfo(transactionInfo);
        transactionInfoService.saveDepositTransactionInfo(transactionInfo2);
    }
}
