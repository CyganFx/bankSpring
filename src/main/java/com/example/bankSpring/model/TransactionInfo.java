package com.example.bankSpring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactioninfo")
public class TransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "postdate")
    private Date postDate;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "operation_amount")
    private double operationAmount;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cardid")
    private CustomerAcc customerAcc;
    @Column(name = "balance")
    private double balance;

    public TransactionInfo() {
    }

    public TransactionInfo(Date postDate, String description, String status, double operationAmount, CustomerAcc customerAcc, double balance) {
        this.postDate = postDate;
        this.description = description;
        this.status = status;
        this.operationAmount = operationAmount;
        this.customerAcc = customerAcc;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(double operationAmount) {
        this.operationAmount = operationAmount;
    }

    public CustomerAcc getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(CustomerAcc customerAcc) {
        this.customerAcc = customerAcc;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}