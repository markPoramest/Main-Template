package com.template.freelance.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "PRODUCT_TYPE")
public class ProductType  implements Serializable {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "PRODUCT_TYPE_ID")
    private Integer productTypeId;
    @Column(name = "PRODUCT_TYPE_CODE")
    private String productCode;
    @Column(name = "PRODUCT_TYPE_NAME")
    private String productName;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;


}
