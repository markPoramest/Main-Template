package com.template.freelance.Controllers;

import com.template.freelance.Beans.ProductTypeBean;
import com.template.freelance.Services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productType")
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @GetMapping("/getAllProductType")
    public List<ProductTypeBean> getAllProduct(){
        try {
            return productTypeService.getAllProductTypeList();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/saveProductType")
    public ResponseEntity<?> saveProduct(@RequestBody ProductTypeBean bean){
        try {
            return new ResponseEntity<>(productTypeService.saveOrUpdateProductType(bean), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteProductType")
    public ResponseEntity<?> deleteProductType(@RequestBody ProductTypeBean bean){
        try {
            return new ResponseEntity<>(productTypeService.deleteProductType(bean), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
