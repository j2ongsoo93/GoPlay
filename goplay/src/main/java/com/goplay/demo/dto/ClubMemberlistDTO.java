package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ClubMemberlistDTO {
    private int listNo;
    private Integer cNo;
    private String id;

    @QueryProjection
    public ClubMemberlistDTO(int listNo, Integer cNo, String id) {
        this.listNo = listNo;
        this.cNo = cNo;
        this.id = id;
    }
}

