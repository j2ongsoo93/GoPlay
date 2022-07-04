package com.goplay.demo.vo;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="notice")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_notice",
		sequenceName = "seq_notice",
		initialValue = 1,
		allocationSize = 1
)
public class Notice {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	nNo;
	private	String			nTitle;
	private	String			nContent;
	private	String			nImg;
	private	String			nVideo;
	private	String			nFile;
	private	LocalDateTime	nDate;
	private	Integer			nHit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
}
