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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="match_offer")
public class MatchOffer {
	@Id
	private	int	moNo	;
	
	@ManyToOne
	@JoinColumn(name="mb_no", insertable = true, updatable = true)
	@JsonBackReference
	private	MatchBoard match_board	;
	
	@ManyToOne
	@JoinColumn(name="c_no", insertable = true, updatable = true)	
	@JsonBackReference
	private	Club club	;
	
	private	String	moUcolor	;
	private	String	moLevel	;
	private	String	moSay	;

	@OneToMany(mappedBy = "match_offer", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<MatchMember> match_member;
}
