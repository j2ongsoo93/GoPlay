package com.goplay.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.dto.MemberDTO;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;

import lombok.Setter;

<<<<<<< HEAD
=======
@RestController 
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
@Setter
@RestController
public class MemberController {
	@Autowired
	private MemberService ms;
	
	@GetMapping("/listMember")
	public void listMember(Model model){
		model.addAttribute("listMember",ms.listMember());
	}
	
	@GetMapping("/selectMember/{id}")
	public void getOne(@PathVariable String id, Model model) {
		model.addAttribute("m",ms.getOne(id));
	}
	
	@PostMapping("/saveMember")
	public String saveMember(Member m) {
		ms.saveMember(m);
		return "redirect:/profile";
	}
	
	@GetMapping("/updateMember/{id}")
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("m", ms.getOne(id));
		return "updateMember";
	}
	
	//해당 로그인 멤버의 종목 4개와 위치2개를 받아옴
	@GetMapping("/findByIdTypeLoc")
	public Member findByIdTypeLoc(String id){
		return ms.findByIdTypeLoc(id);
	}
}
