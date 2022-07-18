package com.goplay.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.service.ReplyService;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
