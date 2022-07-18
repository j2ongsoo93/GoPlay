package com.goplay.demo.controller;

import java.util.List;

import com.goplay.demo.dto.MemberDTOChangHee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

@Setter
@RestController
public class MemberController {
	@Autowired
	private MemberService ms;

	@GetMapping("/listMember")
	public List<Member> listMember(Model model){
		return ms.findAll();
	}
	
	@GetMapping("/selectMember")
	public Member getMember(String id) {
		return ms.getById(id);
	}
	
	@PostMapping("/saveMember")
	public String saveMember(Member m) {
		ms.saveMember(m);
		return "redirect:/profile";
	}
	
	@GetMapping("/updateMember/{id}")
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("m", ms.getById(id));
		return "updateMember";
	}
	
	//해당 로그인 멤버의 종목 4개와 위치2개를 받아옴
	@GetMapping("/findByIdTypeLoc")
	public Member findByIdTypeLoc(String id){
		return ms.findByIdTypeLoc(id);
	}


	@GetMapping("/login")
	public ModelAndView loginMember() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.html");
		return modelAndView;
	}

	@GetMapping("/login/error")
	public ModelAndView loginError(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		//model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
		modelAndView.addObject("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
		modelAndView.setViewName("login.html");
		return modelAndView;
	}


	//커뮤니티 게시판 동호회원 목록 출력
	@GetMapping("/findClubMemberCno")
	public List<MemberDTOChangHee> findClubMemberCno() {
		int cNo=1;
		return ms.findClubMemberCno(cNo);
	}
}
