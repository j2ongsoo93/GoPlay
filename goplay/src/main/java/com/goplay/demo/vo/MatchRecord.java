package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name="match_record")
@SequenceGenerator(
		name = "seq_match_record",
		sequenceName = "seq_match_record",
		initialValue = 1,
		allocationSize = 1
)
public class MatchRecord {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	recNo	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mb_no")
//	@JsonIgnore
	private	MatchBoard match_board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_no")
//	@JsonIgnore
	private	Club club;
	
	private	int	win	;
	private	int	draw	;
	private	int	lose	;
}
