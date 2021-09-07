package com.template.freelance.Beans;

import lombok.Data;

@Data
public class HistorySellBean {
  private Integer sellId;
  private String brand;
  private String productType;
  private String productName;
  private String tel;
  private String startSellDate;
  private String endOfWarranty;
  private String paymentMethod;
  private String condition;
  private Double price;
//  @JsonSerialize(using = CustomDateSerializer.class)
//  @JsonDeserialize(using = CustomDateDeserializer.class)
}
