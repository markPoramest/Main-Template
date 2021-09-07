package com.template.freelance.Services;

import com.template.freelance.Beans.ProductTypeBean;
import com.template.freelance.Beans.SellBean;
import com.template.freelance.Models.Customer;
import com.template.freelance.Models.Product;
import com.template.freelance.Models.ProductType;
import com.template.freelance.Models.Sell;
import com.template.freelance.Repositories.CustomerRepository;
import com.template.freelance.Repositories.ProductRepository;
import com.template.freelance.Repositories.ProductTypeRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Log4j
public class CustomerService {
  @Autowired private ProductRepository productRepository;
  @Autowired private CustomerRepository customerRepository;

  public ResponseEntity<?> getCustomerByIdCard(String idCardNo) {
    log.info("==== Method : getCustomerByIdCard ====");
    try{
    Customer customer = customerRepository.getCustomerByIdCardNo(idCardNo);
    if (customer != null) {
      SellBean sellBean = new SellBean();
      sellBean.setCustomerId(customer.getCustomerId());
      sellBean.setFirstName(customer.getFirstName());
      sellBean.setLastName(customer.getLastName());
      sellBean.setAddress(customer.getAddress());
      sellBean.setIdCardNo(customer.getIdCardNo());
      sellBean.setTel(customer.getTel());
      return new ResponseEntity<>(sellBean, HttpStatus.OK);
    }else{
      throw new NullPointerException();
    }
    }catch (Exception e){
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
  }
}
