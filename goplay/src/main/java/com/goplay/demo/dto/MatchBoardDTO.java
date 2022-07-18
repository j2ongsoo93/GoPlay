package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchBoardDTO {
    private	int	mb_no	;
    private int homeClub;
    private	Integer	awayClub;
    private LocalDateTime mbDate	;
    private	String	mbType	;
    private	String	mbLoc1	;
    private	String	mbLoc2	;
    private	String	mbStadium	;
    private	String		mbFee	;
    private	String	homeUcolor	;
    private	String	awayUcolor	;
    private	String	homeLevel	;
    private	String	awayLevel	;
    private	String	homeSay	;
    private	String	awaySay	;
    private	Integer	hScore	;
    private	Integer	aScore	;
    private	String	mbStat	;

    @QueryProjection
    public MatchBoardDTO(int mb_no, int homeClub, Integer awayClub, LocalDateTime mbDate, String mbType, String mbLoc1, String mbLoc2, String mbStadium, String mbFee, String homeUcolor, String awayUcolor, String homeLevel, String awayLevel, String homeSay, String awaySay, Integer hScore, Integer aScore, String mbStat) {
        this.mb_no = mb_no;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.mbDate = mbDate;
        this.mbType = mbType;
        this.mbLoc1 = mbLoc1;
        this.mbLoc2 = mbLoc2;
        this.mbStadium = mbStadium;
        this.mbFee = mbFee;
        this.homeUcolor = homeUcolor;
        this.awayUcolor = awayUcolor;
        this.homeLevel = homeLevel;
        this.awayLevel = awayLevel;
        this.homeSay = homeSay;
        this.awaySay = awaySay;
        this.hScore = hScore;
        this.aScore = aScore;
        this.mbStat = mbStat;
    }
}
