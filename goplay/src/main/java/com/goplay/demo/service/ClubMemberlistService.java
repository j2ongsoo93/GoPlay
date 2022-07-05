package com.goplay.demo.service;

import com.goplay.demo.dao.ClubMemberlistDAO;
import com.goplay.demo.vo.ClubMemberlist;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class ClubMemberlistService {
    @Autowired
    private ClubMemberlistDAO dao;

    public List<ClubMemberlist> findByCno(int cNo){
        return dao.findAllByClub_cNoOrderByListNoAsc(cNo);
    }
}
