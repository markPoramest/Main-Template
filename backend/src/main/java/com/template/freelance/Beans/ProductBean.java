package com.template.freelance.Beans;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.template.freelance.Config.CustomDateDeserializer;
import com.template.freelance.Config.CustomDateSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ProductBean {

  private Integer productId;
  private String productName;
  private Double price;
  private Integer productTypeId;
  private String productType;
  private Integer amount;
  private String description;
  private Integer brand;
  private String brandName;
  private Integer condition;
  private String conditionDesc;
  private Integer warranty;
  private Integer status;
//  @JsonSerialize(using = CustomDateSerializer.class)
//  @JsonDeserialize(using = CustomDateDeserializer.class)
}
