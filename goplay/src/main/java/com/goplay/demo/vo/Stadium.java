package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="stadium")
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {
	@Id
	private	int		stdNo	;
	private	String	stdName	;
	private	String	stdType	;
	private	String	address	;
	private	String	tel	;
	private	String	aTime	;
	private	String	holiday	;
	private	String	homepage	;
	private	String	stdImg	;
	private	String	stdLoc1	;
	private	String	stdLoc2	;
	private	String	latitude	;
	private	String	longitude	;
}
