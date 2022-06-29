package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
