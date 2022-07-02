package com.goplay.demo.vo;

import java.util.Date;
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
@Table(name="reply")
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	@Id
	private	int	rNo	;
	
	@ManyToOne
	@JoinColumn(name = "b_no", insertable = true, updatable = true)
	@JsonBackReference
	private	Board board	;
	
	private	String	rContent	;
	private	Date	rDate	;
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;

}
