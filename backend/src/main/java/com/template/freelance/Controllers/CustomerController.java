package com.template.freelance.Controllers;

import com.template.freelance.Beans.ProductTypeBean;
import com.template.freelance.Services.CustomerService;
import com.template.freelance.Services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/getCustomerByIdCardNo/{idCardNo}")
    public ResponseEntity<?> getCustomerByIdCardNo(@PathVariable String idCardNo){
        try {
            return  new ResponseEntity<>(customerService.getCustomerByIdCard(idCardNo), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
