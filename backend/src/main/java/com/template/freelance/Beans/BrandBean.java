package com.template.freelance.Beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class BrandBean{

    private Integer brandId;
    private String brandName;
}
