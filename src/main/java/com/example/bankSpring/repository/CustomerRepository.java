package com.example.bankSpring.repository;


import com.example.bankSpring.model.CustomerAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerAcc, Integer> {
//    @Query("SELECT u FROM CustomerAcc u WHERE u.cardId = :cardId")
//    CustomerAcc getCustomerAccByCardId(@Param("cardId") String cardId);
}
