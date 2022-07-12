package com.goplay.demo.dto;

<<<<<<< HEAD
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
	private String id;
=======
import java.time.LocalDateTime;
import java.util.Date;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class BoardDTO {
	
	private	int	bNo;
	private	String	bTitle;
	private	String	bContent;
	private	String	bImg;
	private	String	bVideo;
	private	String	bFile;
	private LocalDateTime bDate;
	private	int		bHit;
	private	LocalDateTime	schDate;
	private	String	schPlace;
	private int 	cNo;
	private int		bType;
	private String 	id;
	
	@QueryProjection
	public BoardDTO(int bNo, String bTitle, String bContent, String bImg, String bVideo, String bFile,
			LocalDateTime bDate, int bHit, LocalDateTime schDate, String schPlace, int cNo, int bType, String id) {
		this.bNo = bNo;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bImg = bImg;
		this.bVideo = bVideo;
		this.bFile = bFile;
		this.bDate = bDate;
		this.bHit = bHit;
		this.schDate = schDate;
		this.schPlace = schPlace;
		this.cNo = cNo;
		this.bType = bType;
		this.id = id;
	}
	
>>>>>>> LHS_new
}
