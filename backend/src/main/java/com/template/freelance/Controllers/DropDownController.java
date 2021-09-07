package com.template.freelance.Controllers;

import com.template.freelance.Services.DropDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dropDown")
public class DropDownController {
    @Autowired
    DropDownService dropDownService;

    @GetMapping("/getProductTypeDropDown")
    public ResponseEntity<Iterable> getProductTypeDropDown() {
        try {
            return dropDownService.getProductTypeDropDown();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getBrandDropDown")
    public ResponseEntity<Iterable> getBrandDropDown() {
        try {
            return dropDownService.getBrandDropDown();
        } catch (Exception e) {
            return null;
        }
    }
}
