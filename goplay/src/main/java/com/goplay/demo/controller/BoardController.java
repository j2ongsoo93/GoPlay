package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.service.BoardService;
import com.goplay.demo.vo.Board;

import lombok.Setter;

@RestController
@Setter
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@GetMapping("/listBoard/{id}")
	public List<Board> findById(@PathVariable String id){
		return bs.listBoards(id);	
	}
}
