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
@Table(name="chatroomjoin")
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomJoin {
	@Id
	private	int	jNo	;
	@ManyToOne
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	private	Integer	rNo	;

}
