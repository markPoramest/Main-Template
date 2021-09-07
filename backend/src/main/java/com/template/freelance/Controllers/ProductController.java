package com.template.freelance.Controllers;

import com.template.freelance.Beans.ProductBean;
import com.template.freelance.Models.Product;
import com.template.freelance.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getAllProduct")
    public List<ProductBean> getAllProduct(){
        try {
            return productService.getAllProductList();
        } catch (Exception e) {
            return null;
        }

    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProduct(@RequestBody ProductBean bean){
        try {
            return new ResponseEntity<>(productService.saveOrUpdateProduct(bean), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductBean bean){
        try {
            return new ResponseEntity<>(productService.deleteProduct(bean), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }
}
