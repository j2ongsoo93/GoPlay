package com.goplay.demo.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.goplay.demo.constant.Role;
import com.goplay.demo.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Column(name="memberid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private	Long memberid;
	
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	String	id	;
	
	private	String	pwd	;
	private	String	phone	;
	private	String	email	;
	private	String	mName	;
	private	String	nickname	;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private	LocalDateTime	birth_date	;
	private	String	gender	;
	private	Integer	soccer	;
	private	Integer footsal	;
	private	Integer	bascketball	;
	private	Integer	footvalleyball	;
	private	String	mImg	;
	private	String	mLoc1	;
	private	String	mLoc2	;
	
	private	String	mStat	;
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private	Role role	;
	public static Member createMember(MemberDTO memberDTO,PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setId(memberDTO.getId());
		String pwd = passwordEncoder.encode(memberDTO.getPwd());
		member.setPwd(pwd);
		member.setPhone(memberDTO.getPhone());
		member.setEmail(memberDTO.getEmail());
		member.setMName(memberDTO.getMName());
		member.setNickname(memberDTO.getNickname());
		member.setBirth_date(memberDTO.getBirth_date());
		member.setGender(memberDTO.getGender());
		member.setSoccer(memberDTO.getSoccer());
		member.setFootsal(memberDTO.getFootsal());
		member.setBascketball(memberDTO.getBascketball());
		member.setFootvalleyball(memberDTO.getFootvalleyball());
		member.setMImg(memberDTO.getMImg());
		member.setMLoc1(memberDTO.getMLoc1());
		member.setMLoc2(memberDTO.getMLoc2());
		member.setMStat("활성");
		member.setRole(Role.USER);
		return member;
	}
	
	
	@OneToMany(mappedBy = "member")
	private List<Club> club;
	
	@OneToMany(mappedBy = "member")
	private List<ClubMemberlist> club_memberlist;
	
	@OneToMany(mappedBy = "member")
	private List<Notice> notice;
	
	@OneToMany(mappedBy = "member")
	private List<Board> board;
	
	@OneToMany(mappedBy = "member")
	private List<ChatRoomJoin> chatroomjoin;
	
	@OneToMany(mappedBy = "member")
	private List<ChatMessage> chatmessage;
	
	@OneToMany(mappedBy = "member")
	private List<Reply> reply;
	
	
}
