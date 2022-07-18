package com.goplay.demo.searchCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MatchBoardSearchCondition {
    private LocalDateTime mbDate;
    private String mbType;
    private String mbLoc1;
    private String mbLoc2;
    private List<String> mbStat;


}
