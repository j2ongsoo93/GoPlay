package com.goplay.demo.service;

import com.goplay.demo.dao.ClubMemberListDAOChangHee;
import com.goplay.demo.dao.ClubMemberlistDAO;
import com.goplay.demo.dto.ClubMemberlistDTO;
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

    @Autowired
    private ClubMemberListDAOChangHee cdao;

    public List<ClubMemberlistDTO> findByCno(int cNo){
        return dao.listClubMember(cNo);
    }

    public int deleteClubMemberList(int cNoChangHee, String currentid){
       return cdao.deleteByClubAndMember(cNoChangHee,currentid);
    }
}
