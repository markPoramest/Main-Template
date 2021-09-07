package com.template.freelance.Repositories;

import com.template.freelance.Models.Brand;
import com.template.freelance.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("select a from Customer a where a.customerId = :customerId and a.status=1")
    Customer getCustomerById(@Param("customerId")Integer customerId);

    @Query("select a from Customer a where a.idCardNo = :idCardNo and a.status=1")
    Customer getCustomerByIdCardNo(@Param("idCardNo")String idCardNo);

    @Query("select a from Customer a where a.tel = :tel and a.status=1")
    Customer getCustomerByTel(@Param("tel")String tel);

    @Query("select a from Customer a where a.status=1 order by a.firstName")
    List<Customer> getCustomerActive();
}
