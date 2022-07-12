package com.goplay.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Club;

import lombok.Setter;

@Controller
@Setter
public class ClubController {
	@Autowired
	private ClubService cs;
	@Autowired
	private MemberService ms;
	
	Page<ClubDTO> clubDTOPage;
	int startPage;
	int endpage;
	//클럽 검색 기능(listClubAll)
	@GetMapping("/listClubAll2")
	@ResponseBody
	public Page<ClubDTO> listClubAll(@PageableDefault(size=1) Pageable pageable, HttpSession session, Model model, 
			@RequestParam(defaultValue = "") String searchText, 
			@RequestParam(defaultValue = "축구") String cType,
			@RequestParam(defaultValue = "") String cloc1,
			@RequestParam(defaultValue = "") String cloc2,
			@RequestParam(defaultValue = "") String size,
			@RequestParam(defaultValue = "") String page
			){


		System.out.println("cType " + cType);
		System.out.println("cloc1 " + cloc1);
		System.out.println("cloc2 " + cloc2);
		System.out.println("size " + size);
		System.out.println("page " + page);
		ClubSearchCondition condition = new ClubSearchCondition();
		condition.setC_type(cType);
		condition.setC_keyword(searchText);
		clubDTOPage =  cs.listClubAll(pageable ,condition); // List타입 to Page 타입으로 변환

		return clubDTOPage;
	}


	@GetMapping("/listClubPageing")
	public String listClubPageing(Model model) {

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endpage);
		model.addAttribute("clubDTOPage", clubDTOPage);
		
		return "listClubAll";
		//return mav;

	}

	@GetMapping("/listClubRecommend")
	public void listClubRecommend() {
		
	}

	@GetMapping ("/findClub/{cNo}")
	@ResponseBody
	public List<ClubDTO> findByCno(@PathVariable int cNo){
		return cs.findByCno(cNo);
	}
}
