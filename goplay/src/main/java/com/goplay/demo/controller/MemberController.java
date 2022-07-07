package com.goplay.demo.controller;

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
	public void listMember(Model model){
		model.addAttribute("listMember",ms.findAll());
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
}
