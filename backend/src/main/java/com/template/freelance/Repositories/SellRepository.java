package com.template.freelance.Repositories;

import com.template.freelance.Models.Customer;
import com.template.freelance.Models.Payment;
import com.template.freelance.Models.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell,Integer> {

    @Query("select a from Sell a where a.status=1")
    List<Sell> getSellAll();

}
