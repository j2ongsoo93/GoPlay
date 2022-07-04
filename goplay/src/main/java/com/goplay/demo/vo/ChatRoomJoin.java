package com.goplay.demo.vo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="chatroomjoin")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "seq_chatroomjoin",
		sequenceName = "seq_chatroomjoin",
		initialValue = 1,
		allocationSize = 1
)
public class ChatRoomJoin {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private	int	jNo	;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = true, updatable = true)
	@JsonBackReference
	private	Member member	;
	private	Integer	rNo	;

}
