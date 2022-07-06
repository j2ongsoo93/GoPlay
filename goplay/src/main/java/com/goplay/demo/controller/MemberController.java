package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public List<Member> listMember(){
		return ms.findAll();
	}
	
	@GetMapping("/selectMember/{id}")
	public String getOne(@PathVariable String id, Model model) {
		model.addAttribute("m",ms.getOne(id));
		return "redirect:/profile";
	}
	
	@PostMapping("/saveMember")
	@ResponseBody
	public String saveMember(Member m) {
		ms.saveMember(m);
		return "redirect:/profile";
	}
	
	@GetMapping("/updateMember/{id}")
	@ResponseBody
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("m", ms.getOne(id));
		return "updateMember";
	}
}
