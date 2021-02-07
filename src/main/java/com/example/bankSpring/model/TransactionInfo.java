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
    private int operationAmount;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "cardid")
    private CustomerAcc customerAcc;

    public TransactionInfo() {
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

    public CustomerAcc getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(CustomerAcc customerAcc) {
        this.customerAcc = customerAcc;
    }

    public int getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(int operationAmount) {
        this.operationAmount = operationAmount;
    }

    public TransactionInfo(Date postDate, String description, String status, int operationAmount, CustomerAcc customerAcc) {
        this.postDate = postDate;
        this.description = description;
        this.status = status;
        this.operationAmount = operationAmount;
        this.customerAcc = customerAcc;
    }
}