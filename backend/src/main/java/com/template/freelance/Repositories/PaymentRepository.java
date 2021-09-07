package com.template.freelance.Repositories;

import com.template.freelance.Models.Customer;
import com.template.freelance.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("select a from Payment a where a.paymentId = :paymentId and a.status=1")
    Customer getPaymentById(@Param("paymentId")Integer paymentId);

}
