package com.goplay.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.goplay.demo.vo.Club;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class MatchBoardDTO {
    private	int	mb_no	;
    private Club club;
    private	Integer	awayClub;
    private Date mbDate	;
    private	String	mbType	;
    private	String	mbLoc1	;
    private	String	mbLoc2	;
    private	String	mbStadium	;
    private	int		mbFee	;
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
    public MatchBoardDTO(int mb_no, Club club, Integer awayClub, Date mbDate, String mbType, String mbLoc1, String mbLoc2, String mbStadium, int mbFee, String homeUcolor, String awayUcolor, String homeLevel, String awayLevel, String homeSay, String awaySay, Integer hScore, Integer aScore, String mbStat) {
        this.mb_no = mb_no;
        this.club = club;
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
    @QueryProjection
    public MatchBoardDTO() {

    }
}
