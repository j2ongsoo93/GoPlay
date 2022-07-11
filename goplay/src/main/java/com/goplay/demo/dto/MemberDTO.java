package com.goplay.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	
	@QueryProjection
	public MemberDTO(String id, String pwd, String phone, String email, String mName, String nickname,
			LocalDateTime birth_date, String gender, Integer soccer, Integer footsal, Integer bascketball,
			Integer footvalleyball, String mImg, String mLoc1, String mLoc2, String mStat, String role) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.email = email;
		this.mName = mName;
		this.nickname = nickname;
		this.birth_date = birth_date;
		this.gender = gender;
		this.soccer = soccer;
		this.footsal = footsal;
		this.bascketball = bascketball;
		this.footvalleyball = footvalleyball;
		this.mImg = mImg;
		this.mLoc1 = mLoc1;
		this.mLoc2 = mLoc2;
		this.mStat = mStat;
		this.role = role;
	}
	
	
	
}
