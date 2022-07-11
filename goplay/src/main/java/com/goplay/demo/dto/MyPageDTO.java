package com.goplay.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class MyPageDTO {
	private List<BoardDTO> board;
	private List<ReplyDTO> reply;
	
	
}
