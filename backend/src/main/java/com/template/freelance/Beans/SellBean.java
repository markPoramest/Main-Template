package com.template.freelance.Beans;

import lombok.Data;

@Data
public class SellBean {
  private Integer sellId;
  private Integer productId;
  private Integer customerId;
  private Integer paymentMethod;
  private Integer fullPaymentMethod;
  private String firstName;
  private String lastName;
  private String idCardNo;
  private String address;
  private String tel;
  private Integer month;
  private Double amount;
  private Double interest;
  private Double payPerMonth;
//  @JsonSerialize(using = CustomDateSerializer.class)
//  @JsonDeserialize(using = CustomDateDeserializer.class)
}
