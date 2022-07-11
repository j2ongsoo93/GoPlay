package com.goplay.demo.vo;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name="club_memberlist")
@SequenceGenerator(
		name = "seq_club_memberlist",
		sequenceName = "seq_club_memberlist",
		initialValue = 1,
		allocationSize = 1
)
public class ClubMemberlist {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	listNo	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_no", insertable = true, updatable = true)
	@JsonBackReference
	private	Club club	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	
	@OneToMany(mappedBy = "club_memberlist")
	@JsonManagedReference
	private List<MatchMember> match_member;
}
