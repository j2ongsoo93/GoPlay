package com.goplay.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubInfoDTO {
	private	int	cNo	;
	private	String id;
	private	String	cName	;
	private	String	cType	;
	private	String	cLoc1	;
	private	String	cLoc2	;
	private	String	cImg	;
	private	String	cIntro	;
	private	String	cStat	;
	private Long memberCount;
	private Long win;
	private Long draw;
	private Long lose;
	private Long recordCount;
	
}
