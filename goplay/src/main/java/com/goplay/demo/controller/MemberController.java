package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goplay.demo.dto.MemberDTO;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;

import lombok.Setter;

@Setter
@RestController
public class MemberController {
	@Autowired
	private MemberService ms;
	
	@GetMapping("/listMember")
	public List<Member> listMember(){
		return ms.findAll();
	}
	
	//해당 로그인 멤버의 종목 4개와 위치2개를 받아옴
	@GetMapping("/findByIdTypeLoc")
	public Member findByIdTypeLoc(String id){
		return ms.findByIdTypeLoc(id);
	}
}
