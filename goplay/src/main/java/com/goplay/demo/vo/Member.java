package com.goplay.demo.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	String	id	;
	
	private	String	pwd	;
	private	String	phone	;
	private	String	email	;
	private	String	mName	;
	private	String	nickname	;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
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
	
	@OneToMany(mappedBy = "member")
	private List<Club> club;
	
	@OneToMany(mappedBy = "member")
	private List<ClubMemberlist> club_memberlist;
	
	@OneToMany(mappedBy = "member")
	private List<Notice> notice;
	
	@OneToMany(mappedBy = "member")
	private List<Board> board;
	
	@OneToMany(mappedBy = "member")
	private List<ChatRoomJoin> chatroomjoin;
	
	@OneToMany(mappedBy = "member")
	private List<ChatMessage> chatmessage;
	
	@OneToMany(mappedBy = "member")
	private List<Reply> reply;
	
	
}
