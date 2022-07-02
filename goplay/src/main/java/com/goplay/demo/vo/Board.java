package com.goplay.demo.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="board")
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id
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
	 
	@ManyToOne
	@JoinColumn(name="c_no" ,insertable = true, updatable = true)
	@JsonBackReference
	private	Club club	;
	
	private	Integer	bType	;
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Reply> reply;

}
