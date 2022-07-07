package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.MyPageDTO;
import com.goplay.demo.service.BoardService;
import com.goplay.demo.service.ReplyService;

import lombok.Setter;

@RestController
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	@Autowired
	private ReplyService rs;
	
	@GetMapping("/listBoardByReply/{id}")	//마이페이지 내 댓글 조회
	public MyPageDTO listBoardByReply(@PathVariable String id){
		MyPageDTO mypage = new MyPageDTO();
		mypage.setBoard(bs.findById(id));
		mypage.setReply(rs.findByReplyId(id));
		return mypage;
	}
	
	@GetMapping("/listBoard/{id}")	//마이페이지 내 게시물 조회 
	public List<BoardDTO> listBoard(@PathVariable String id){
		return bs.findById(id);
	}
	
	@GetMapping("/deleteBoard/{bNo}")
	public String delete(@PathVariable int bNo) {
		bs.deleteBorad(bNo);
		return "";
	}
}
