package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AddressCityDTO {
    private	int	acNo	;
    private	String	acName	;

    @QueryProjection
    public AddressCityDTO(int acNo, String acName) {
        this.acNo = acNo;
        this.acName = acName;
    }
}
