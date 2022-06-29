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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_board")
public class MatchBoard {
	@Id
	private	int	mb_no	;
	
	@ManyToOne
	@JoinColumn(name = "home_club", insertable = true, updatable = true)
	@JsonBackReference
	private	Club 	club;
	
	private	Integer	awayClub;
	private	Date	mbDate	;
	private	String	mbType	;
	private	String	mbLoc1	;
	private	String	mbLoc2	;
	private	String	mbStadium	;
	private	int		mbFee	;
	private	String	homeUcolor	;
	private	String	awayUcolor	;
	private	String	homeLevel	;
	private	String	awayLevel	;
	private	String	homeSay	;
	private	String	awaySay	;
	private	Integer	hScore	;
	private	Integer	aScore	;
	private	String	mbStat	;
	
	@OneToMany(mappedBy = "match_board", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchOffer> match_offer;
	
	@OneToMany(mappedBy = "match_board", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchMember> match_member;
	
	@OneToMany(mappedBy = "match_board", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchRecord> match_record;
}
