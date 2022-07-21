package com.goplay.demo.service;

import com.goplay.demo.dao.MatchOfferDAO;
import com.goplay.demo.dao.MatchOfferDAOCustom;
import com.goplay.demo.dto.MatchOfferDTO;
import com.goplay.demo.vo.MatchOffer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Setter
public class MatchOfferService {
    @Autowired
    private MatchOfferDAOCustom daoCustom;
    @Autowired
    private MatchOfferDAO dao;

    public List<MatchOfferDTO> listMatchOffer(int mbNo){
        return daoCustom.listMatchOffer(mbNo);
    }

    public List<MatchOfferDTO> moByMoNo(int moNo){
        return daoCustom.moByMoNo(moNo);
    }

    @Transactional
    public void insertMatchOffer(MatchOffer mo){dao.save(mo);}


}
