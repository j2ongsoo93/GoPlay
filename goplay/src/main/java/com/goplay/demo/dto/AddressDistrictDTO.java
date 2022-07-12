package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AddressDistrictDTO {
    private	int	adNo	;
    private	String	adName	;
    private Integer acNO;

    @QueryProjection
    public AddressDistrictDTO(int adNo, String adName, Integer acNO) {
        this.adNo = adNo;
        this.adName = adName;
        this.acNO = acNO;
    }


}
