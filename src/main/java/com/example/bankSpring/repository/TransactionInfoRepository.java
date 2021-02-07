package com.example.bankSpring.repository;


import com.example.bankSpring.model.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Integer> {
}
