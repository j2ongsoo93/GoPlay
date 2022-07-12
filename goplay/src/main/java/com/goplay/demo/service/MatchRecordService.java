package com.goplay.demo.service;

import com.goplay.demo.dao.MatchRecordDAOCustom;
import com.goplay.demo.dto.MatchRecordDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class MatchRecordService {
    @Autowired
    private MatchRecordDAOCustom dao;

    public List<MatchRecordDTO> matchRecord(int cNo){
        return dao.matchRecord(cNo);
    }
}
