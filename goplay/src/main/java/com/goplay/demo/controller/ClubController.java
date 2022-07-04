package com.goplay.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.ClubDTO;

import lombok.Setter;

@Controller
@Setter
public class ClubController {
	@Autowired
	private ClubService cs;
	
	//클럽 검색 기능(listClubAll)
	@GetMapping("/listClubAll")
	//@ResponseBody
	public void listClubAll(@PageableDefault(size=1) Pageable pageable, HttpSession session, Model model, 
			@RequestParam(required = false, defaultValue = "") String searchText, 
			@RequestParam(required = false, defaultValue = "축구") String radio,
			@RequestParam(required = false, defaultValue = "") String validationCustom01,
			@RequestParam(required = false, defaultValue = "") String validationCustom02){
		ClubSearchCondition condition = new ClubSearchCondition();
		condition.setC_type(radio);
		condition.setC_loc1(validationCustom01);
		//condition.setC_loc2(validationCustom02);
		condition.setC_keyword(searchText);
		//condition.setC_type("족구");
		//condition.setC_loc1("인천시");
		//condition.setC_loc2(validationCustom02);
		//condition.setC_keyword("족구");
		System.out.println("citycitycity " + validationCustom01);
		Page<ClubDTO> clubDTOPage =  cs.listClubAll(pageable ,condition); // List타입 to Page 타입으로 변환
		
		System.out.println("-----------------------------------");
		System.out.println("getNumber " + clubDTOPage.getNumber());
		System.out.println("getNumberOfElements " + clubDTOPage.getNumberOfElements());
		System.out.println("getSize " + clubDTOPage.getSize());
		System.out.println("getTotalElements " + clubDTOPage.getTotalElements());
		System.out.println("getTotalPages " + clubDTOPage.getTotalPages());
		System.out.println("get(Stream) " + clubDTOPage.get());
		System.out.println("getContent " + clubDTOPage.getContent());
		System.out.println("getPageable " + clubDTOPage.getPageable());
		System.out.println("getSort " + clubDTOPage.getSort());
		System.out.println("clubDTOPage : " + clubDTOPage);
		System.out.println("-----------------------------------");
		
		model.addAttribute("list", clubDTOPage);
		
		int startPage = Math.max(1, clubDTOPage.getPageable().getPageNumber() - 4); //현재 페이지 넘버, 최소 값 1으로 설정
		int endpage = Math.min(clubDTOPage.getTotalPages(), clubDTOPage.getPageable().getPageNumber() + 4); //
		
		System.out.println("pageable.getPageNumber() " + pageable.getPageNumber());
		System.out.println("clubDTOPage.getTotalPages() " + clubDTOPage.getTotalPages());
		System.out.println("startPage - " + startPage);
		System.out.println("endpage - " + endpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endpage);
		model.addAttribute("clubDTOPage", clubDTOPage);
	}
	
	@GetMapping("/listClubShowCount")
	public void listClubShowCount() {
		
	}
}
