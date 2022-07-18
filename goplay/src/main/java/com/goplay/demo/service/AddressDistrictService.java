package com.goplay.demo.service;

import com.goplay.demo.dao.AddressDistrictDAO;
import com.goplay.demo.dto.AddressDistrictDTO;
import com.goplay.demo.vo.AddressDistrict;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class AddressDistrictService {
    @Autowired
    private AddressDistrictDAO dao;

    public List<AddressDistrictDTO> listDistrict(String acName){
        return dao.listDistrict(acName);
    }
}
