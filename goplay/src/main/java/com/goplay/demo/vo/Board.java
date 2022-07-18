package com.goplay.demo.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="board")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_board",
		sequenceName = "seq_board",
		initialValue = 1,
		allocationSize = 1
)
public class Board {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	bNo	;
	private	String	bTitle	;
	private	String	bContent	;
	private	String	bImg	;
	private	String	bVideo	;
	private	String	bFile	;

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private LocalDateTime bDate	;
	private	int	bHit	;
	private	LocalDateTime	schDate	;
	private	String	schPlace	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="c_no" ,insertable = true, updatable = true)
	@JsonBackReference
	private	Club club	;
	
	private	Integer	bType	;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	
	@OneToMany(mappedBy = "board")
	@JsonManagedReference
	private List<Reply> reply;
}
