package com.goplay.demo.controller;

import com.goplay.demo.service.AddressDistrictService;
import com.goplay.demo.vo.AddressDistrict;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Setter
public class AddressDistrictController {
    @Autowired
    private AddressDistrictService as;


    }
}
