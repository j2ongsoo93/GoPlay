package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class MatchDateDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime mbDate;
    private long cnt;

    @QueryProjection
    public MatchDateDTO(LocalDateTime mbDate, long cnt) {
        this.mbDate = mbDate;
        this.cnt = cnt;
    }
}
