package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="match_member")
@SequenceGenerator(
		name = "seq_match_member",
		sequenceName = "seq_match_member",
		initialValue = 1,
		allocationSize = 1
)
public class MatchMember {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	mmNo	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mb_no", insertable = true, updatable = true)
	@JsonIgnore
	private	MatchBoard match_board	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mo_no", insertable = true, updatable = true)
	@JsonIgnore
	private MatchOffer match_offer	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "list_no", insertable = true, updatable = true)
	@JsonIgnore
	private	ClubMemberlist club_memberlist	;
}
