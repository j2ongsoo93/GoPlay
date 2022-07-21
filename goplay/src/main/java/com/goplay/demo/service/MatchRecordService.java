package com.goplay.demo.service;

import com.goplay.demo.dao.MatchRecordDAO;
import com.goplay.demo.dao.MatchRecordDAOCustom;
import com.goplay.demo.dto.MatchRecordDTO;
import com.goplay.demo.vo.MatchRecord;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Setter
public class MatchRecordService {
    @Autowired
    private MatchRecordDAOCustom daoCustom;
    @Autowired
    private MatchRecordDAO dao;

    public List<MatchRecordDTO> matchRecord(int cNo){
        return daoCustom.matchRecord(cNo);
    }

    @Transactional
    public void insertRecord(MatchRecord mr){dao.save(mr);}
}
