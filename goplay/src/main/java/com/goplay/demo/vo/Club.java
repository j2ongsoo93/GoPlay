package com.goplay.demo.vo;

import java.util.List;

import javax.persistence.*;

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
@SequenceGenerator(
		name = "seq_club",
		sequenceName = "seq_club",
		initialValue = 1,
		allocationSize = 1
)
public class Club {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	cNo	;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
	
	@OneToMany(mappedBy = "club")
	private List<ClubMemberlist> club_memberList;
	
	@OneToMany(mappedBy = "club")
	private List<MatchBoard> match_board;
	
	@OneToMany(mappedBy = "club")
	private List<MatchOffer> match_offer;
	
	@OneToMany(mappedBy = "club")
	private List<MatchRecord> match_record;
	
	@OneToMany(mappedBy = "club")
	private List<ChatRoom> chatroom;
	
	@OneToMany(mappedBy = "club")
	private List<Board> board;
}
