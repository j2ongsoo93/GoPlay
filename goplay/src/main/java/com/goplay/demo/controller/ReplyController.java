package com.goplay.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.goplay.demo.vo.Board;
import com.goplay.demo.vo.Member;
import com.goplay.demo.vo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.service.ReplyService;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import com.goplay.demo.service.BoardService;

@Controller
@Setter
public class ReplyController {
	
	@Autowired
	private ReplyService rs;
	@Autowired
	private BoardService bs;


	//동호회 커뮤니티 내 클럽 정보
	@GetMapping("/listReply")
	@ResponseBody
	public List<ReplyDTO> listReply(@RequestParam Integer bNo) {
		//int bNo=2;//게시글
		System.out.println("bNo " + bNo);
		return rs.listReply(bNo);
	}

	//댓글 달기
	@PostMapping("/addReply")
	@ResponseBody
	public void addReply(@RequestParam String currentid,
						@RequestParam String textAreaValue,
						@RequestParam int nowBoardNum){

		Board boardBno = new Board();
			boardBno.setBNo(nowBoardNum);
		Member memberId = new Member();
			memberId.setId(currentid);
		Reply reply = new Reply(0, boardBno, textAreaValue, LocalDateTime.now(), memberId);
		rs.addReply(reply);
	}

	@GetMapping("/deleteRelply/{rNO}")
	@ResponseBody
	public String delete(@PathVariable int rNO) {
		rs.delete(rNO);
		return "redirect:/";
	}
	
	@GetMapping("/listReply/{id}")
	@ResponseBody
	public List<ReplyDTO> listReply(@PathVariable String id) {

		return rs.findByReplyId(id);
	}
}
