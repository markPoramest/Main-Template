package com.template.freelance.Controllers;

import com.template.freelance.Beans.BrandBean;
import com.template.freelance.Services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
  @Autowired BrandService brandService;

  @GetMapping("/getAllBrand")
  public List<BrandBean> getAllBrand() {
    try {
      return brandService.getAllBrand();
    } catch (Exception e) {
      return null;
    }
  }

  @PostMapping("/saveBrand")
  public ResponseEntity<?> saveBrand(@RequestBody BrandBean bean) {
      try {
          return new ResponseEntity<>(brandService.saveOrUpdateBrand(bean), HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
      }
  }

    @PostMapping("/deleteBrand")
    public ResponseEntity<?> deleteBrand(@RequestBody BrandBean bean) {
        try {
            return new ResponseEntity<>(brandService.deleteBrand(bean), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
