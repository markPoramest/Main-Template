package com.template.freelance.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "SELL")
public class Sell implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SELL_ID")
  private Integer sellId;

  @JoinColumn(name = "CUSTOMER", referencedColumnName = "CUSTOMER_ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Customer customer;

  @JoinColumn(name = "PRODUCT", referencedColumnName = "PRODUCT_ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Product product;

  @Column(name = "INSTALLMENT_MONTH")
  private Integer installmentMonth;

  @Column(name = "PAYMENT_METHOD")
  private Integer paymentMethod;

  @Column(name = "INTEREST")
  private Double interest;

  @Column(name = "paymentPerMonth")
  private Double paymentPerMonth;

  @Column(name = "SELL_DATE")
  private Date sellDate;

  @Column(name = "STATUS")
  private Integer status;
}
