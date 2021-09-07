package com.template.freelance.Services;

import com.template.freelance.Beans.ProductBean;
import com.template.freelance.Beans.ProductTypeBean;
import com.template.freelance.Models.Product;
import com.template.freelance.Models.ProductType;
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
public class ProductTypeService {
  @Autowired private ProductRepository productRepository;
  @Autowired private ProductTypeRepository productTypeRepository;

  public List<ProductTypeBean> getAllProductTypeList() {
    log.info("==== Method : getAllProductTypeList ====");
    List<ProductType> productList = productTypeRepository.getProductTypeActive();
    List<ProductTypeBean> productBeans = new ArrayList<>();
    for (ProductType product : productList) {
      ProductTypeBean productBean = new ProductTypeBean();
      productBean.setProductTypeId(product.getProductTypeId());
      productBean.setProductCode(product.getProductCode());
      productBean.setProductName(product.getProductName());
      productBeans.add(productBean);
    }
    return productBeans;
  }

  @Transactional
  public ResponseEntity<?> saveOrUpdateProductType(ProductTypeBean bean) {
    log.info("==== Method : saveOrUpdateProductType ====");
    try {
      ProductType product;
      if(bean.getProductTypeId()!=null){
        product = productTypeRepository.getProductTypeById(bean.getProductTypeId());
      }else{
        product = new ProductType();
      }
      product.setProductName(bean.getProductName());
      product.setStatus(1);
      product.setUpdatedDate(new Date());
      productTypeRepository.save(product);
    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @Transactional
  public ResponseEntity<?> deleteProductType(ProductTypeBean bean) {
    log.info("==== Method : deleteProductType ====");
    try {
      List<Product> product = productRepository.getProductByProductType(bean.getProductTypeId());
      if (product != null && product.size() > 0) {
       log.info("Error : This productTypeId was used in Product");
        return new ResponseEntity<>(true, HttpStatus.IM_USED);
      }
      ProductType productType = productTypeRepository.getProductTypeById(bean.getProductTypeId());
      productType.setStatus(2);
      productType.setUpdatedDate(new Date());
      productTypeRepository.save(productType);
    } catch (Exception e) {
      log.info("Error : " + e.getMessage());
      TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
