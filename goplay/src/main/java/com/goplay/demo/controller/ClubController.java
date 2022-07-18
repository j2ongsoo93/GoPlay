package com.goplay.demo.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.service.MemberService;
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
	}

	@GetMapping("/listClubRecommend")
	public void listClubRecommend() {
	}

	// 클럽번호로 클럽 검색(matchBoard에서 사용)
	@GetMapping("/findClub/{cNo}")
	@ResponseBody
	public List<ClubDTO> findClub(@PathVariable int cNo){
		return cs.findClub(cNo);
	}
}
