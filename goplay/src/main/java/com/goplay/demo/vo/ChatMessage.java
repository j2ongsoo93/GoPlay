package com.goplay.demo.vo;

import java.time.LocalDateTime;
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
@Table(name="chatmessage")
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
	@Id
	private	int	msgNo	;
	private	String	msg	;
	private	String	msgImg	;
	private	String	msgVideo	;
	private	String	msgFile	;
	private	LocalDateTime	msgTime	;
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	
	@ManyToOne
	@JoinColumn(name = "r_no", insertable = true, updatable = true)
	@JsonBackReference
	private	ChatRoom chatroom	;

}
