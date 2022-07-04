package com.goplay.demo.vo;

import java.util.List;

import javax.persistence.*;

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
@SequenceGenerator(
		name = "seq_chatroom",
		sequenceName = "seq_chatroom",
		initialValue = 1,
		allocationSize = 1
)
public class ChatRoom {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	rNo	;
	private	Integer	rType	;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="c_no" ,insertable = true, updatable = true)
	@JsonBackReference
	private	Club club	;
	
	@OneToMany(mappedBy = "chatroom")
	@JsonManagedReference
	private List<ChatMessage> chatMessage;
}
