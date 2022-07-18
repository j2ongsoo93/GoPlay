package com.goplay.demo.controller;

import com.goplay.demo.dto.AddressCityDTO;
import com.goplay.demo.service.AddressCityService;
import com.goplay.demo.vo.AddressCity;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Setter
public class AddressCityController {
    @Autowired
    private AddressCityService as;

    @GetMapping("/listCity")
    public List<AddressCityDTO> listAddress(){
        return as.listAddress();
    }

    @GetMapping("/listDistrict/{acName}")
    public List<AddressCity> findByAcName(@PathVariable String acName){
        return as.findByAcName(acName);
    }

}
