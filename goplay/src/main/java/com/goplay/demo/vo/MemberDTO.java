package com.goplay.demo.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private	String	id	;
	private	String	pwd	;
	private	String	phone	;
	private	String	email	;
	private	String	mName	;
	private	String	nickname	;
	private	LocalDateTime	birth_date	;
	private	String	gender	;
	private	Integer	soccer	;
	private	Integer footsal	;
	private	Integer	bascketball	;
	private	Integer	footvalleyball	;
	private	String	mImg	;
	private	String	mLoc1	;
	private	String	mLoc2	;
	private	String	mStat	;
	private	String	role	;
}
