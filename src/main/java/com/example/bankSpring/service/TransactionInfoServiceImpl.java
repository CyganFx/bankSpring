package com.example.bankSpring.service;

import com.example.bankSpring.model.TransactionInfo;
import com.example.bankSpring.model.User;
import com.example.bankSpring.repository.TransactionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionInfoServiceImpl implements TransactionInfoService {

    @Autowired
    private TransactionInfoRepository transactionInfoRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<TransactionInfo> findTransactionList(String username) {
        User user = userService.findByUsername(username);
        return user.getCustomerAcc().getTransactionInfos();
    }

    @Override
    public void saveDepositTransactionInfo(TransactionInfo transactionInfo) {
        transactionInfoRepository.save(transactionInfo);
    }
}
