package com.goplay.demo.controller;


import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.dto.MatchRecordDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.goplay.demo.service.MatchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.vo.MatchBoard;

import lombok.Setter;

import java.util.List;

@Controller
@Setter
public class MatchBoardController {
	@Autowired
	private MatchBoardService ms;
	@Autowired
	private MatchRecordService mrs;

	//매치검색페이지로 이동
	@GetMapping("/matchBoard")
	public String listMatchBoard(Model model){
		return "matchBoard";
	}

	//매치검색
	@GetMapping("/findMatch")
	@ResponseBody
	public Page<MatchBoardDTO> findMatchBoard(MatchBoardSearchCondition condition){
		PageRequest pageRequest1 = PageRequest.of(1, 3);
		return ms.searchMatchBoard(condition, pageRequest1);
	}

	//매치등록, 수정
	@PostMapping("/saveMatchBoard")
	@ResponseBody
	public void insertBoard(MatchBoard mb) {
		ms.saveBoard(mb);
	}

	//매치등록화면으로 이동
	@GetMapping("/insertMatchBoard")
	public String insertBoard(Model model){
		return "insertMatchBoard";
	}

	//매치수정화면으로 이동
	@GetMapping("/updateMatchBoard")
	public void updateBoard(){
	}

	//내 매치 검색
	@GetMapping("/myMatchEnd/{id}")
	@ResponseBody
	public List<MatchBoard> myMatch(@PathVariable String id){
		return ms.myMatch(id);
	}

	//전적 검색
	@GetMapping("/matchRecord/{cNo}")
	@ResponseBody
	public List<MatchRecordDTO> matchRecord(@PathVariable int cNo){
		return mrs.matchRecord(cNo);
	}
}
