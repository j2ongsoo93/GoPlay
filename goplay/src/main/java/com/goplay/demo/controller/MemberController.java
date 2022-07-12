package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;

import lombok.Setter;

@RestController 
@Setter
public class MemberController {
	@Autowired
	private MemberService ms;
	
	@GetMapping("/listMember")
	public List<Member> listMember(Model model){
		//model.addAttribute("listMember",ms.listMember());
		return ms.findAll();
	}
	
//	@GetMapping("/selectMember/{id}")
//	public Member getMember(@PathVariable String id) {
////		System.out.println(ms.getById(id));
//		return ms.getById(id);
//	}
	
	@GetMapping("/selectMember")
	public Member getMember(String id) {
//		System.out.println(ms.getById(id));
//		model.addAttribute("m", ms.getById(id));
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
}
