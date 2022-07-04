package com.goplay.demo.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "match_board")
@SequenceGenerator(
		name = "seq_match_board",
		sequenceName = "seq_match_board",
		initialValue = 1,
		allocationSize = 1
)
public class MatchBoard {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	mb_no	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "home_club")
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
	
	@OneToMany(mappedBy = "match_board")
	private List<MatchOffer> match_offer = new ArrayList<>();
	
	@OneToMany(mappedBy = "match_board")
	private List<MatchMember> match_member = new ArrayList<>();
	
	@OneToMany(mappedBy = "match_board")
	private List<MatchRecord> match_record = new ArrayList<>();
}
