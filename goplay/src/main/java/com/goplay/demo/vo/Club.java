package com.goplay.demo.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="club")
@AllArgsConstructor
@NoArgsConstructor
public class Club {
	@Id
	private	int	c_no	;
	
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member	member;
	
	private	String	cName	;
	private	String	cType	;
	private	String	cLoc1	;
	private	String	cLoc2	;
	private	String	cImg	;
	private	String	cIntro	;
	private	String	cStat	;
	
	@OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ClubMemberlist> club_memberList;
	
	@OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchBoard> match_board;
	
	@OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchOffer> match_offer;
	
	@OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchRecord> match_record;
	
	@OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ChatRoom> chatroom;
	
	@OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Board> board;
}
