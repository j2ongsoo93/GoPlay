package com.goplay.demo.vo;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="reply")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_reply",
		sequenceName = "seq_reply",
		initialValue = 1,
		allocationSize = 1
)
public class Reply {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	rNo	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "b_no", insertable = true, updatable = true)
	@JsonBackReference
	private	Board board	;
	
	private	String	rContent	;

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private LocalDateTime rDate	;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;

}
