package com.goplay.demo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
public class ClubDTO {
	private	int	cNo	;
	private	String id;
	private	String	cName	;
	private	String	cType	;
	private	String	cLoc1	;
	private	String	cLoc2	;
	private	String	cImg	;
	private	String	cIntro	;
	private	String	cStat	;

	@QueryProjection
	public ClubDTO(int cNo, String id, String cName, String cType, String cLoc1, String cLoc2, String cImg, String cIntro, String cStat) {
		this.cNo = cNo;
		this.id = id;
		this.cName = cName;
		this.cType = cType;
		this.cLoc1 = cLoc1;
		this.cLoc2 = cLoc2;
		this.cImg = cImg;
		this.cIntro = cIntro;
		this.cStat = cStat;
	}
}
