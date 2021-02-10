package com.example.bankSpring.repository;


import com.example.bankSpring.model.CustomerAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerAcc, Integer> {
    @Query("SELECT c FROM CustomerAcc c WHERE c.cardNumber = :cardNumber")
    CustomerAcc getCustomerByCardNumber(@Param("cardNumber") String cardNumber);
}
