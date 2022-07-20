package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchOfferDTO {
    private int moNo;
    private int mbNo;
    private int cNo;
    private String moUcolor;
    private String moLevel;
    private String moSay;
    private LocalDateTime moDate;

    @QueryProjection
    public MatchOfferDTO(int moNo, int mbNo, int cNo, String moUcolor, String moLevel, String moSay, LocalDateTime moDate) {
        this.moNo = moNo;
        this.mbNo = mbNo;
        this.cNo = cNo;
        this.moUcolor = moUcolor;
        this.moLevel = moLevel;
        this.moSay = moSay;
        this.moDate = moDate;
    }
}
