package com.example.bankSpring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_acc")
public class CustomerAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardid")
    private int cardId;
    @Column(name = "balance")
    private int balance;

    public CustomerAcc() {
    }

    public CustomerAcc(int balance) {
        this.balance = balance;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "customerAcc", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionInfo> transactionInfos = new ArrayList<>();

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TransactionInfo> getTransactionInfos() {
        return transactionInfos;
    }

    public void setTransactionInfos(List<TransactionInfo> transactionInfos) {
        this.transactionInfos = transactionInfos;
    }
}
