package com.template.freelance.Beans;

import lombok.Data;

@Data
public class ProductTypeBean {

  private Integer productTypeId;
  private String productCode;
  private String productName;
//  @JsonSerialize(using = CustomDateSerializer.class)
//  @JsonDeserialize(using = CustomDateDeserializer.class)
}
