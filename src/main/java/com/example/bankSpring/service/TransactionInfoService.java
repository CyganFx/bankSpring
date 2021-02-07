package com.example.bankSpring.service;

import com.example.bankSpring.model.TransactionInfo;

import java.util.List;


public interface TransactionInfoService {
    List<TransactionInfo> getAllTransactionInfos();
    List<TransactionInfo> findTransactionList(String username);
    void saveDepositTransactionInfo(TransactionInfo transactionInfo);
}
