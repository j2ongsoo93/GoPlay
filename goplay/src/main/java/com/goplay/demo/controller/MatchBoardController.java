package com.goplay.demo.controller;

import java.util.List;

import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.vo.MatchBoard;

import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Setter
public class MatchBoardController {
	@Autowired
	private MatchBoardService ms;
	
	//매치검색 초기목록
	@GetMapping("/listMatchBoard")
	@ResponseBody
	public List<MatchBoard> listMatchBoard(){
		return ms.listMatchBoard();
	}

	//매치검색
	@GetMapping("/findMatch")
	@ResponseBody
	public List<MatchBoard> findMatchBoard(){
		MatchBoardSearchCondition condition1 = new MatchBoardSearchCondition();
		condition1.setMbType("축구");
		condition1.setMbLoc1("인천시");
		return ms.searchMatchBoard(condition1);
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



}
