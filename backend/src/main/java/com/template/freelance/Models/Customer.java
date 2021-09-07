package com.template.freelance.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CUSTOMER_ID")
  private Integer customerId;

  @Column(name = "ID_CARD_NO" , length = 13)
  private String idCardNo;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "TEL")
  private String tel;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  public Customer(String tel) {
    this.tel = tel;
    this.status = 1;
    this.setUpdatedDate(new Date());
  }
}
