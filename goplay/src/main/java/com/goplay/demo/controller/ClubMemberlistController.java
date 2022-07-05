package com.goplay.demo.controller;

import com.goplay.demo.dto.ClubMemberlistDTO;
import com.goplay.demo.service.ClubMemberlistService;
import com.goplay.demo.vo.ClubMemberlist;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Setter
public class ClubMemberlistController {
    @Autowired
    private ClubMemberlistService cs;

    @GetMapping("/listClubMemberlist/{cNo}")
    public List<ClubMemberlistDTO> listClubMemberlist(@PathVariable int cNo){
        return cs.findByCno(cNo);
    }
}
