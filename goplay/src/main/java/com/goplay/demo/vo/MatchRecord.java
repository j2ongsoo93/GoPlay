package com.goplay.demo.vo;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="match_record")
public class MatchRecord {
	@Id
	private	int	recNo	;
	
	@ManyToOne
	@JoinColumn(name = "mb_no", insertable = true, updatable = true)
	@JsonBackReference
	private	MatchBoard match_board;
	
	@ManyToOne
	@JoinColumn(name = "c_no", insertable = true, updatable = true)
	@JsonBackReference
	private	Club club;
	
	private	int	win	;
	private	int	draw	;
	private	int	lose	;
}
