package com.example.bankSpring.service;


import com.example.bankSpring.model.CustomerAcc;
import com.example.bankSpring.model.TransactionInfo;
import com.example.bankSpring.model.User;
import com.example.bankSpring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;


@Service
public class CustomerAccServiceImpl implements CustomerAccService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionInfoService transactionInfoService;
    @Override
    public List<CustomerAcc> getAllAccounts() {
        return customerRepository.findAll();
    }



    @Override
    public CustomerAcc findCustomer(String username) {
        User user = userService.findByUsername(username);
        CustomerAcc customerAcc = user.getCustomerAcc();
        return customerAcc;
    }

    @Override
    public void deposit(double amount, Principal principal) {
        User user =  userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        customerAcc.setBalance((int) (customerAcc.getBalance() + amount));
        customerRepository.save(customerAcc);
        Date date = new Date();
        TransactionInfo transactionInfo = new TransactionInfo(date, "Deposit to the account", "success", (int) amount, customerAcc);
        transactionInfoService.saveDepositTransactionInfo(transactionInfo);
    }

    @Override
    public void withdraw(double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        CustomerAcc customerAcc = user.getCustomerAcc();
        customerAcc.setBalance((int) (customerAcc.getBalance() - amount));
        customerRepository.save(customerAcc);
        Date date = new Date();
        TransactionInfo transactionInfo = new TransactionInfo(date, "Withdraw from account", "success", (int) amount, customerAcc);
        transactionInfoService.saveDepositTransactionInfo(transactionInfo);
    }
}
