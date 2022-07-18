package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

@Setter
@Controller
public class MemberController {
	@Autowired
	private MemberService ms;

	@GetMapping("/listMember")
	@ResponseBody
	public List<Member> listMember(Model model){
		return ms.findAll();
	}

	@GetMapping("/selectMember")
	@ResponseBody
	public Member getMember(String id) {
		return ms.getById(id);
	}

	@PostMapping("/updateMember")
	@ResponseBody
	public String saveMember(Member m) {
		ms.saveMember(m);
		return "redirect:/edit-profile";
	}

	@GetMapping("/updateMember/{id}")
	@ResponseBody
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("m", ms.getById(id));
		return "updateMember";
	}

	//해당 로그인 멤버의 종목 4개와 위치2개를 받아옴
	@GetMapping("/findByIdTypeLoc")
	@ResponseBody
	public Member findByIdTypeLoc(String id){
		return ms.findByIdTypeLoc(id);
	}

	@GetMapping("/asdf")
	public String profilePrivacy(Model model){return "profile-privacy";}
}