package com.template.freelance.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "BRAND")
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "BRAND_NAME")
    private String brandName;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
}
