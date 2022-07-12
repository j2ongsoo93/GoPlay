package com.goplay.demo.controller;

import java.util.List;
<<<<<<<<< Temporary merge branch 1

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
=========
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.ClubDTOInterface;
import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.service.BoardService;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.service.ReplyService;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.Member;
import com.querydsl.core.Tuple;

import lombok.Setter;

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
>>>>>>>>> Temporary merge branch 2
	}
}
