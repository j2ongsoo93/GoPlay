package com.goplay.demo.vo;

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
@Table(name="match_offer")
@SequenceGenerator(
		name = "seq_match_offer",
		sequenceName = "seq_match_offer",
		initialValue = 1000,
		allocationSize = 1
)
public class MatchOffer {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	moNo	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mb_no", insertable = true, updatable = true)
	@JsonIgnore
	private	MatchBoard match_board	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="c_no", insertable = true, updatable = true)	
	@JsonIgnore
	private	Club club	;
	
	private	String	moUcolor	;
	private	String	moLevel	;
	private	String	moSay	;

	@OneToMany(mappedBy = "match_offer")
	private List<MatchMember> match_member;
}
