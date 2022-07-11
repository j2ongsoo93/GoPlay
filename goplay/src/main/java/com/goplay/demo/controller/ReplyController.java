package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.service.ReplyService;

import lombok.Setter;

@RestController
@Setter
public class ReplyController {
	
	@Autowired
	private ReplyService rs;
	
	@GetMapping("/deleteRelply/{rNO}")
	public String delete(@PathVariable int rNO) {
		rs.delete(rNO);
		return "redirect:/";
	}
	
	@GetMapping("/listReply/{id}")
	public List<ReplyDTO> listReply(@PathVariable String id){
		
		return rs.findByReplyId(id);
	}
}