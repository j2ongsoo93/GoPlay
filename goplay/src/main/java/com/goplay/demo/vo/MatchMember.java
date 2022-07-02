package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="match_member")
public class MatchMember {
	@Id
	private	int	mmNo	;
	
	@ManyToOne
	@JoinColumn(name = "mb_no", insertable = true, updatable = true)
	@JsonBackReference
	private	MatchBoard match_board	;
	
	@ManyToOne
	@JoinColumn(name = "mo_no", insertable = true, updatable = true)
	@JsonBackReference
	private MatchOffer match_offer	;
	
	@ManyToOne
	@JoinColumn(name = "list_no", insertable = true, updatable = true)
	@JsonBackReference
	private	ClubMemberlist club_memberlist	;
}
