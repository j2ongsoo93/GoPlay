package com.goplay.demo.dto;


import java.time.LocalDateTime;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReplyDTO {
	
	private int rNo;
	private int bNo;
	private String rContent;
	private LocalDateTime rDate;
	private String id;
	
	@QueryProjection
	public ReplyDTO(int rNo, int bNo, String rContent, LocalDateTime rDate, String id) {
		this.rNo = rNo;
		this.bNo = bNo;
		this.rContent = rContent;
		this.rDate = rDate;
		this.id = id;
	}
}

//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class ReplyDTO {
//	private int rNo;
//	private int bNo;
//	private String rContent;
//	private Date rDate;
//	private String id;
//}

