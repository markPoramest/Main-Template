package com.template.freelance.Controllers;

import com.template.freelance.Beans.ProductBean;
import com.template.freelance.Beans.SellBean;
import com.template.freelance.Services.ProductService;
import com.template.freelance.Services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sell")
public class SellController {
    @Autowired
    SellService sellService;

    @PostMapping("/sellProduct")
    public ResponseEntity<?> saveProduct(@RequestBody SellBean bean){
        try {
            return new ResponseEntity<>(sellService.sellProduct(bean), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getSellHistory")
    public ResponseEntity<?> getSellHistory(){
        try {
            return new ResponseEntity<>(sellService.getAllSellList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
