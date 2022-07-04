package com.goplay.demo.vo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="stadium")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_stadium",
		sequenceName = "seq_stadium",
		initialValue = 1,
		allocationSize = 1
)
public class Stadium {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
