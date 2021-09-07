package com.template.freelance.Services;

import com.template.freelance.Beans.ProductBean;
import com.template.freelance.Constants.Condition;
import com.template.freelance.Models.Brand;
import com.template.freelance.Models.Product;
import com.template.freelance.Models.ProductType;
import com.template.freelance.Repositories.BrandRepository;
import com.template.freelance.Repositories.ProductRepository;
import com.template.freelance.Repositories.ProductTypeRepository;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
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
public class ProductService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;
  @Autowired private BrandRepository brandRepository;

  public List<ProductBean> getAllProductList() {
    log.info("==== Method : getAllProductList ====");
    List<Product> productList = productRepository.getAllProductActive();
    List<ProductBean> productBeans = new ArrayList<>();
    for (Product product : productList) {
      ProductBean productBean = new ProductBean();
      productBean.setProductId(product.getProductId());
      productBean.setProductName(product.getProductName());
      productBean.setBrand(product.getBrand().getBrandId());
      productBean.setBrandName(product.getBrand().getBrandName());
      productBean.setPrice(product.getPrice());
      productBean.setAmount(product.getAmount());
      productBean.setProductTypeId(product.getProductType().getProductTypeId());
      productBean.setProductType(product.getProductType().getProductName());
      productBean.setConditionDesc(this.checkCondition(product.getCondition()));
      productBean.setWarranty(product.getWarranty());
      productBean.setCondition(product.getCondition());
      productBean.setDescription(product.getDescription());
      productBeans.add(productBean);
    }
    return productBeans;
  }

  @Transactional
  public ResponseEntity<?> saveOrUpdateProduct(ProductBean bean) {
    log.info("==== Method : saveOrUpdateProduct ====");
    try {
      Product product;
      if(bean.getProductId()!=null){
        product = productRepository.getProductActiveById(bean.getProductId());
      }else{
        product = new Product();
      }
      ProductType productType = productTypeRepository.getProductTypeById(bean.getProductTypeId());
      Brand brand = brandRepository.getBrandById(bean.getBrand());
      product.setProductName(bean.getProductName());
      product.setPrice(bean.getPrice());
      product.setAmount(bean.getAmount());
      product.setProductType(productType);
      product.setBrand(brand);
      product.setDescription(bean.getDescription());
      product.setStatus(1);
      product.setWarranty(bean.getWarranty());
      product.setCondition(bean.getCondition());
      product.setUpdatedDate(new Date());
      productRepository.save(product);
    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<?> deleteProduct(ProductBean bean) {
    log.info("==== Method : deleteProduct ====");
    try {
      Product product;
      if (bean.getProductId() != null) {
        product = productRepository.getProductActiveById(bean.getProductId());
        product.setStatus(2);
        product.setUpdatedDate(new Date());
        productRepository.save(product);
      }
    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  private String checkCondition(Integer condition){
    if(condition==null){
      return "";
    }
    else if(condition==1){
      return Condition.FIRST_HAND_PRODUCT;
    }else{
      return Condition.SECOND_HAND_PRODUCT;
    }
  }
}
