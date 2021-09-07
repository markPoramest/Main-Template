package com.template.freelance.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "Payment")
public class Payment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PAYMENT_ID")
  private Integer paymentId;

  @JoinColumn(name = "CUSTOMER", referencedColumnName = "CUSTOMER_ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Customer customer;

  @JoinColumn(name = "SELL", referencedColumnName = "SELL_ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Sell sell;

  @Column(name = "PAYMENT_METHOD")
  private Integer paymentMethod;

  @Column(name = "FULL_PAYMENT_METHOD")
  private Integer fullPaymentMethod;

  @Column(name = "AMOUNT")
  private Double amount;

  @Column(name = "PAYMENT_DATE")
  private Date paymentDate;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;
}
