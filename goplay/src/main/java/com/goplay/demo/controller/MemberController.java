package com.goplay.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goplay.demo.dto.MemberDTO;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController 
@Setter
@RequiredArgsConstructor
public class MemberController {
	@Autowired
	private MemberService ms;
	
	private final MemberService memberserivce;
	private final PasswordEncoder passwordEncoder;
	
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
	
	

	@GetMapping("/insertMember")//  /new
	public ModelAndView insertMember(Model model) {
		model.addAttribute("memberDTO", new MemberDTO());
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("insertMember");
        return modelAndView;
		//return "/insertMember"; //member/memberForm
	}
	@PostMapping("/insertMember")
	public ModelAndView memberForm( MemberDTO memberDTO, Model model) {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("insertMember");
		
		try {
			Member member = Member.createMember(memberDTO, passwordEncoder);
			memberserivce.saveMember(member);
			
		}catch (IllegalStateException e) {
			model.addAttribute("errorMessage",e.getMessage());
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/main.html");
		return modelAndView;
	}
	
	 @GetMapping("/login")
	    public String loginMember() {
	        return "/login";
	    }

	    @GetMapping("/login/error")
	    public String loginError(Model model) {
	        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
	        return "/login";
	    }
	
}
