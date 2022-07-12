package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MatchRecordDTO {
    private	Integer	win	;
    private	Integer	draw	;
    private	Integer	lose	;

    @QueryProjection
    public MatchRecordDTO(Integer win, Integer draw, Integer lose) {
        this.win = win;
        this.draw = draw;
        this.lose = lose;
    }
}
