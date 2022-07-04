package com.goplay.demo.service;

import com.goplay.demo.dao.AddressCityDAO;
import com.goplay.demo.vo.AddressCity;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class AddressCityService {
    @Autowired
    private AddressCityDAO dao;

    public List<AddressCity> listAddress(){
        return dao.findAllByOrderByAcNo();
    }
    public List<AddressCity> findByAcName(String acName){return dao.findAllByAcName(acName);}
}
