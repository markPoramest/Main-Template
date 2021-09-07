package com.template.freelance.Services;

import com.template.freelance.Beans.BrandBean;
import com.template.freelance.Beans.ProductTypeBean;
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
import java.util.List;

@Service
@Log4j
public class BrandService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;
  @Autowired private BrandRepository brandRepository;

  public List<BrandBean> getAllBrand() {
    log.info("==== Method : getAllBrand ====");
    List<Brand> brandList = brandRepository.getBrandActive();
    List<BrandBean> brandBeans = new ArrayList<>();
    for (Brand brand : brandList) {
      BrandBean bean = new BrandBean();
      bean.setBrandId(brand.getBrandId());
      bean.setBrandName(brand.getBrandName());
      brandBeans.add(bean);
    }
    return brandBeans;
  }

  @Transactional
  public ResponseEntity<?> saveOrUpdateBrand(BrandBean bean) {
    log.info("==== Method : saveOrUpdateBrand ====");
    try {
      Brand brand;
      if(bean.getBrandId()!=null){
        brand = brandRepository.getBrandById(bean.getBrandId());
      }else{
        brand = new Brand();
      }
      brand.setBrandName(bean.getBrandName());
      brand.setStatus(1);
      brand.setUpdatedDate(new Date());
      brandRepository.save(brand);
    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<?> deleteBrand(BrandBean bean) {
    log.info("==== Method : deleteBrand ====");
    try {
      List<Product> product = productRepository.getProductByBrandId(bean.getBrandId());
      if(product != null && product.size()>0){
        return new ResponseEntity<>(true, HttpStatus.IM_USED);
      }
      Brand brand;
      brand = brandRepository.getBrandById(bean.getBrandId());
      brand.setStatus(2);
      brand.setUpdatedDate(new Date());
      brandRepository.save(brand);
    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
