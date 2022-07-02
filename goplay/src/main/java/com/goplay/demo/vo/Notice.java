package com.goplay.demo.vo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="notice")
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
	@Id
	private	int	nNo;
	private	String			nTitle;
	private	String			nContent;
	private	String			nImg;
	private	String			nVideo;
	private	String			nFile;
	private	LocalDateTime	nDate;
	private	Integer			nHit;
	
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
}
