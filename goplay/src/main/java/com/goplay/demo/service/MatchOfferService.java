package com.goplay.demo.service;

import com.goplay.demo.dao.AddressDistrictDAO;
import com.goplay.demo.dao.MatchOfferDAO;
import com.goplay.demo.dto.AddressDistrictDTO;
import com.goplay.demo.dto.MatchOfferDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class MatchOfferService {
    @Autowired
    private MatchOfferDAO dao;

    public List<MatchOfferDTO> listMatchOffer(int mbNo){
        return dao.listMatchOffer(mbNo);
    }
}
