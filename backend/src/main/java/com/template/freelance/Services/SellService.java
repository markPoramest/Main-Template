package com.template.freelance.Services;

import com.template.freelance.Beans.HistorySellBean;
import com.template.freelance.Beans.ProductBean;
import com.template.freelance.Beans.SellBean;
import com.template.freelance.Constants.SellConstant;
import com.template.freelance.Models.*;
import com.template.freelance.Repositories.*;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.time.DateUtils;
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
public class SellService {
  @Autowired private ProductRepository productRepository;
  @Autowired private CustomerRepository customerRepository;
  @Autowired private PaymentRepository paymentRepository;
  @Autowired private SellRepository sellRepository;

  public List<HistorySellBean> getAllSellList() {
    log.info("==== Method : getAllSellList ====");
    List<Sell> productList = sellRepository.getSellAll();
    List<HistorySellBean> sellBeans = new ArrayList<>();
    for (Sell sell : productList) {
      HistorySellBean sellBean = new HistorySellBean();
      sellBean.setSellId(sell.getSellId());
      if (sell.getPaymentMethod() == 1) {
        sellBean.setPaymentMethod(SellConstant.FULL_PAYMENT);
      } else {
        sellBean.setPaymentMethod(SellConstant.INSTALLMENT);
      }
      sellBean.setStartSellDate(DateUtility.getDateString(sell.getSellDate()));
      sellBean.setBrand(sell.getProduct().getBrand().getBrandName());
      sellBean.setProductType(sell.getProduct().getProductType().getProductName());
      sellBean.setProductName(sell.getProduct().getProductName());
      Date endWarranty = DateUtils.addMonths(sell.getSellDate(), sell.getProduct().getWarranty());
      sellBean.setEndOfWarranty(DateUtility.getDateString(endWarranty));
      sellBean.setTel(sell.getCustomer().getTel());
      if (sell.getProduct().getCondition() == 1)
        sellBean.setCondition(SellConstant.FIRST_HAND_PRODUCT);
      else sellBean.setCondition(SellConstant.SECOND_HAND_PRODUCT);
      sellBean.setPrice(sell.getProduct().getPrice());
      sellBeans.add(sellBean);
    }
    return sellBeans;
  }

  @Transactional
  public ResponseEntity<?> sellProduct(SellBean sellBean) {
    log.info("==== Method : sellProduct  ====" + sellBean);
    try {
      Sell sell = new Sell();
      Payment payment = new Payment();
      Product product = productRepository.getProductActiveById(sellBean.getProductId());
      if (product.getAmount() <= 0) {
        return new ResponseEntity<>(SellConstant.OUT_OF_STOCK, HttpStatus.BAD_REQUEST);
      }
      if (sellBean.getPaymentMethod() == 1) {
        payment.setFullPaymentMethod(sellBean.getFullPaymentMethod());
        Customer customer;
        customer = customerRepository.getCustomerByTel(sellBean.getTel());
        if (customer == null) {
          customer = customerRepository.save(new Customer(sellBean.getTel()));
        }
        payment.setCustomer(customer);
        sell.setCustomer(customer);
        payment.setAmount((double) product.getAmount());
      } else {
        Customer customer;
        if (sellBean.getCustomerId() != null) {
          customer = customerRepository.getCustomerById(sellBean.getCustomerId());
        } else {
          customer = new Customer();
        }
        customer.setFirstName(sellBean.getFirstName());
        customer.setLastName(sellBean.getLastName());
        customer.setAddress(sellBean.getAddress());
        customer.setIdCardNo(sellBean.getIdCardNo());
        customer.setTel(sellBean.getTel());
        customer.setStatus(1);
        customer.setUpdatedDate(new Date());
        customerRepository.save(customer);
        payment.setCustomer(customer);
        payment.setAmount(sellBean.getPayPerMonth());
        sell.setCustomer(customer);
        sell.setInstallmentMonth(sellBean.getMonth());
        sell.setInterest(sellBean.getInterest());
        sell.setPaymentPerMonth(sellBean.getPayPerMonth());
      }
      product.setAmount(product.getAmount() - 1);

      sell.setPaymentMethod(sellBean.getPaymentMethod());
      sell.setProduct(product);
      sell.setSellDate(new Date());
      sell.setStatus(1);

      payment.setPaymentMethod(sellBean.getPaymentMethod());
      payment.setPaymentDate(new Date());
      payment.setUpdatedDate(new Date());
      payment.setStatus(1);

      productRepository.save(product);
      sell = sellRepository.save(sell);
      payment.setSell(sell);
      paymentRepository.save(payment);

    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
