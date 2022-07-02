package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.ClubDTO;

import lombok.Setter;

@RestController
@Setter
public class ClubController {
	@Autowired
	private ClubService cs;
	
	//클럽 검색 기능
	@GetMapping("/findClub")
	@ResponseBody
	public List<ClubDTO> findClub(Model model){
		ClubSearchCondition condition = new ClubSearchCondition();
		condition.setC_type("축구");
		condition.setC_loc1("인천시");
		condition.setC_loc2("연수구");
		condition.setC_keyword("인천");
		 return cs.searchClub(condition);
	}
}
