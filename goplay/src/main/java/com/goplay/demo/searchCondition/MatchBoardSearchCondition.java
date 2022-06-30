package com.goplay.demo.searchCondition;

import lombok.Data;

import java.util.Date;

@Data
public class MatchBoardSearchCondition {
    private Date mbDate;
    private String mbType;
    private String mbLoc1;
    private String mbLoc2;
    private String mbStat;
}
