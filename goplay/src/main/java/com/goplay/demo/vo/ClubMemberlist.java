package com.goplay.demo.vo;

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

import lombok.Data;

@Entity
@Data
@Table(name="club_memberlist")
public class ClubMemberlist {
	@Id
	private	int	listNo	;
	
	@ManyToOne
	@JoinColumn(name = "c_no", insertable = true, updatable = true)
	@JsonBackReference
	private	Club club	;
	
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	
	@OneToMany(mappedBy = "club_memberlist", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchMember> match_member;
}
