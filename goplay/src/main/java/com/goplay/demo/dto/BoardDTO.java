package com.goplay.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private	int	bNo	;
	private	String	bTitle	;
	private	String	bContent	;
	private	String	bImg	;
	private	String	bVideo	;
	private	String	bFile	;
	private	Date	bDate	;
	private	int	bHit	;
	private	Date	schDate	;
	private	String	schPlace	;
}
