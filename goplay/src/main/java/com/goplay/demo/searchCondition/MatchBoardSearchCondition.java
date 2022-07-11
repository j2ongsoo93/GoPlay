package com.goplay.demo.searchCondition;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MatchBoardSearchCondition {
    private LocalDateTime mbDate;
    private String mbType;
    private String mbLoc1;
    private String mbLoc2;
    private String mbStat;
}
