package com.goplay.demo.dto;

import java.time.LocalDateTime;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

//	@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class BoardDTO {
//	private	int	bNo	;
//	private	String	bTitle	;
//	private	String	bContent	;
//	private	String	bImg	;
//	private	String	bVideo	;
//	private	String	bFile	;
//	private	Date	bDate	;
//	private	int	bHit	;
//	private	Date	schDate	;
//	private	String	schPlace	;
//	private String id;
}
