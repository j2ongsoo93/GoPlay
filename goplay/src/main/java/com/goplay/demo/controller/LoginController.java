package com.goplay.demo.controller;

import java.security.Principal;
import java.util.List;

import com.goplay.demo.constant.Role;
import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Member;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

@Setter
@Controller
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private MemberService ms;
    @Autowired
    private MemberDAO dao;

    private final PasswordEncoder passwordEncoder;



    @PostMapping("/saveMember")
    @ResponseBody
    public String saveMember(Member m) {
        ms.saveMember(m);
        return "redirect:/edit-profile";
    }

    /*
    @GetMapping("/insertMember")//
    public ModelAndView insertMember(@ModelAttribute MemberDTO memberDTO, Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("MemberDTO",memberDTO);
        modelAndView.setViewName("insertMember");
        return modelAndView;
        //return "/insertMember"; //member/memberForm
    }
*/
    @GetMapping("/insertMember")//
    public ModelAndView insertMember(@ModelAttribute MemberDTO memberDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("MemberDTO",memberDTO);
        modelAndView.setViewName("sign-up");
        return modelAndView;
        //return "/insertMember"; //member/memberForm
    }
    @PostMapping("/insertMember")
    public ModelAndView memberForm(@ModelAttribute MemberDTO memberDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("MemberDTO",memberDTO);
        modelAndView.setViewName("sign-up");

        try {
            Member member = Member.createMember(memberDTO, passwordEncoder);
            ms.saveMember(member);

        }catch (IllegalStateException e) {
            modelAndView.addObject("errorMessage",e.getMessage());
            //model.addAttribute("errorMessage",e.getMessage());
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/index.html");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginMember() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

    @GetMapping("/login/error")
    public ModelAndView loginError(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        //model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        modelAndView.addObject("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        modelAndView.setViewName("sign-in");
        return modelAndView;
    }

    @GetMapping("/loginmember")
    @ResponseBody
    public String loginmember(Principal principal) {
        return dao.findById(principal.getName()).getId();
    }
}
