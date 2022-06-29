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
@Table(name="chatroom")
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
	@Id
	private	int	rNo	;
	private	Integer	rType	;
	
	@ManyToOne
	@JoinColumn(name="c_no" ,insertable = true, updatable = true)
	@JsonBackReference
	private	Club club	;
	
	@OneToMany(mappedBy = "chatroom", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ChatMessage> chatMessage;
}
