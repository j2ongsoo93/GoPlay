package com.goplay.demo.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private LocalDateTime mbDate	;

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private LocalDateTime mbTime;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm", timezone="Asia/Seoul")
	private	String	mbType	;
	private	String	mbLoc1	;
	private	String	mbLoc2	;
	private	String	mbStadium	;
	private	String	mbFee	;
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
