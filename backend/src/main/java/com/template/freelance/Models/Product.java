package com.template.freelance.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "PRODUCT")
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @Column(name = "PRODUCT_NAME")
  private String productName;

  @Column(name = "PRICE")
  private Double price;

  @JoinColumn(name = "PRODUCT_TYPE", referencedColumnName = "PRODUCT_TYPE_ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private ProductType productType;

  @JoinColumn(name = "BRAND", referencedColumnName = "BRAND_ID", nullable = false)
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Brand brand;

  @Column(name = "AMOUNT")
  private Integer amount;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "CONDITIONS")
  private Integer condition;

  @Column(name = "WARRANTY")
  private Integer warranty;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;
}
