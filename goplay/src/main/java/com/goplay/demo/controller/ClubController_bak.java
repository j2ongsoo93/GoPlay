//package com.goplay.demo.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.goplay.demo.dao.MemberDAO;
//import com.goplay.demo.dto.ClubDTO;
//import com.goplay.demo.searchCondition.ClubSearchCondition;
//import com.goplay.demo.service.ClubService;
//import com.goplay.demo.service.MatchBoardService;
//import com.goplay.demo.service.MemberService;
//import com.goplay.demo.vo.Club;
//
//import lombok.Setter;
//
//@Controller
//@Setter
//public class ClubController_bak {
//	@Autowired
//	private ClubService cs;
//	@Autowired
//	private MemberService ms;
//	
//	Page<ClubDTO> clubDTOPage;
//	int startPage;
//	int endpage;
//	//클럽 검색 기능(listClubAll)
//	@GetMapping("/listClubAll2")
//	@ResponseBody
//	public Page<ClubDTO> listClubAll(@PageableDefault(size=1) Pageable pageable, HttpSession session, Model model, 
//			@RequestParam(defaultValue = "") String searchText, 
//			@RequestParam(defaultValue = "축구") String cType,
//			@RequestParam(defaultValue = "") String cloc1,
//			@RequestParam(defaultValue = "") String cloc2,
//			@RequestParam(defaultValue = "") String size,
//			@RequestParam(defaultValue = "") String page
//			){
//		
//		System.out.println("cType " + cType);
//		System.out.println("cloc1 " + cloc1);
//		System.out.println("cloc2 " + cloc2);
//		System.out.println("size " + size);
//		System.out.println("page " + page);
//		ClubSearchCondition condition = new ClubSearchCondition();
//		condition.setC_type(cType);
//		//condition.setC_loc1(cloc1); //우선 주석했기 때문에 pageing 시 null 나옴
//		//condition.setC_loc2(cloc2); //우선 주석했기 때문에 pageing 시 null 나옴
//		condition.setC_keyword(searchText);
//		clubDTOPage =  cs.listClubAll(pageable ,condition); // List타입 to Page 타입으로 변환
//		System.out.println("-----------------------------------");
////		System.out.println("getNumber " + clubDTOPage.getNumber());
////		System.out.println("getNumberOfElements " + clubDTOPage.getNumberOfElements());
////		System.out.println("getSize " + clubDTOPage.getSize());
////		System.out.println("getTotalElements " + clubDTOPage.getTotalElements());
////		System.out.println("getTotalPages " + clubDTOPage.getTotalPages());
////		System.out.println("get(Stream) " + clubDTOPage.get());
////		System.out.println("getContent " + clubDTOPage.getContent());
////		System.out.println("getPageable " + clubDTOPage.getPageable());
////		System.out.println("getSort " + clubDTOPage.getSort());
////		System.out.println("clubDTOPage : " + clubDTOPage);
//		System.out.println("-----------------------------------");
////		model.addAttribute("list", clubDTOPage);
//		
//		startPage = Math.max(1, clubDTOPage.getPageable().getPageNumber() - 4); //현재 페이지 넘버, 최소 값 1으로 설정
//		endpage = Math.min(clubDTOPage.getTotalPages(), clubDTOPage.getPageable().getPageNumber() + 4); //
//		
////		System.out.println("pageable.getPageNumber() " + pageable.getPageNumber());
////		System.out.println("clubDTOPage.getTotalPages() " + clubDTOPage.getTotalPages());
////		System.out.println("startPage - " + startPage);
////		System.out.println("endpage - " + endpage);
//		
////		mav.addObject("startPage", startPage);
////		mav.addObject("endPage", endpage);
////		mav.addObject("clubDTOPage", clubDTOPage);
////		mav.setViewName("/listClubAll");
////		model.addAttribute("startPage", startPage);
////		model.addAttribute("endPage", endpage);
////		model.addAttribute("clubDTOPage", clubDTOPage);
////	
//		
////		  HttpHeaders header = new HttpHeaders();
////		  header.add("Content-Type", "application/json; charset=UTF-8");
////
////		  return new ResponseEntity<Page<ClubDTO>>(clubDTOPage,header,HttpStatus.OK);
//		return clubDTOPage;
//	}
////	@GetMapping("/listClubPageing")
////	public String listClubPageing(Model model) {
////		//ModelAndView mav = new ModelAndView();
//////		mav.addObject("startPage", startPage);
//////		mav.addObject("endPage", endpage);
//////		mav.addObject("clubDTOPage", clubDTOPage);
//////		mav.setViewName("redirect:/listClubAll");
////		model.addAttribute("startPage", startPage);
////		model.addAttribute("endPage", endpage);
////		model.addAttribute("clubDTOPage", clubDTOPage);
////		
////		return "redirect:/listClubAll";
////		//return mav;
////
////	}	
//	
//	@GetMapping("/listClubRecommend")
//	public void listClubRecommend() {
//		
//	}
//	
////	@GetMapping("/findById")
////	public void findById(String id) {
////		ms.findById(id);
////	}
//}
