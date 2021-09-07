package com.template.freelance.Services;

import com.template.freelance.Beans.DropDownBean;
import com.template.freelance.Beans.ProductBean;
import com.template.freelance.Models.Brand;
import com.template.freelance.Models.Product;
import com.template.freelance.Models.ProductType;
import com.template.freelance.Repositories.BrandRepository;
import com.template.freelance.Repositories.ProductRepository;
import com.template.freelance.Repositories.ProductTypeRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Log4j
public class DropDownService {
  @Autowired private BrandRepository brandRepository;
  @Autowired private ProductTypeRepository productTypeRepository;

  public ResponseEntity<Iterable> getProductTypeDropDown() {
    log.info("==== Method : getProductTypeDropDown ====");
    Iterable<ProductType> tmpList = productTypeRepository.getProductTypeActive();
    List<DropDownBean> results = new LinkedList<>();
    if (tmpList != null) {
      for (ProductType obj : tmpList) {
        results.add(new DropDownBean(obj.getProductTypeId(), obj.getProductCode(),
                obj.getProductName()));
      }
    }
    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  public ResponseEntity<Iterable> getBrandDropDown() {
    log.info("==== Method : getBrandDropDown ====");
    Iterable<Brand> tmpList = brandRepository.getBrandActive();
    List<DropDownBean> results = new LinkedList<>();
    if (tmpList != null) {
      for (Brand obj : tmpList) {
        results.add(new DropDownBean(obj.getBrandId(),"",
                obj.getBrandName()));
      }
    }
    return new ResponseEntity<>(results, HttpStatus.OK);
  }
}
