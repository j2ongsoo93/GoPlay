package com.goplay.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.vo.MatchBoard;

import lombok.Setter;

@Controller
@Setter
public class MatchBoardController {
	@Autowired
	private MatchBoardService ms;
	
	//매치등록, 수정
	@PostMapping("/saveMatchBoard")
	@ResponseBody
	public void insertBoard(MatchBoard mb) {
		ms.saveBoard(mb);
	}
	//매치등록화면으로 이동
	@GetMapping("/insertMatchBoard")
	public void insertBoard(Model model){
	}
	//매치수정화면으로 이동
	@GetMapping("/updateMatchBoard")
	public void updateBoard(){
	}
	//매치검색
	@GetMapping("/findMatch")
	@ResponseBody
	public List<MatchBoard> findMatchBoard(	@Nullable Date mbDate,
											@Nullable String mbType,
											@Nullable String mbLoc1,
											@Nullable String mbLoc2,
											@Nullable String mbStat){
		return ms.findMatchBoard(mbDate,mbType,mbLoc1,mbLoc2,mbStat);
	}




	@GetMapping("/listMatchBoard")
	@ResponseBody
	public List<MatchBoard> listMatchBoard(){
		return ms.listMatchBoard();
	}

	@GetMapping("/findMatchBoard")
	@ResponseBody
	public List<MatchBoard> findBy(){
		return ms.findMatchBoardByMbStat("종료");
	}



}
