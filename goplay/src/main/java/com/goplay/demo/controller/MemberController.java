package com.goplay.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.goplay.demo.dto.MemberDTOChangHee;
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

/*	@PostMapping("/updateMember")
	@ResponseBody
	public String saveMember(Member m) {
		ms.saveMember(m);
		return "redirect/edit-profile";
	}*/

	@PostMapping("/profile-update")
	@ResponseBody
	public ModelAndView saveMember(@RequestBody HashMap<String, String> map, Model model ) {
		List<String> mbStat = new ArrayList<>();
		mbStat.add(map.get("memberNickName"));
		/*model.addAttribute("m", ms.getById(id));*/
		System.out.println(map);
		System.out.println(map.get("id"));
		System.out.println(map.get("memberNickName"));
		ModelAndView mav = new ModelAndView("profile-update");
		return mav;
	}
	/*@PostMapping("/updateMember/{id}")
	@ResponseBody
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("m", ms.getById(id));
		return "updateMember";
	}*/

	//해당 로그인 멤버의 종목 4개와 위치2개를 받아옴
	@GetMapping("/findByIdTypeLoc")
	@ResponseBody
	public Member findByIdTypeLoc(String id){
		return ms.findByIdTypeLoc(id);
	}

	@GetMapping("/ProfilePrivacy")
	public String profilePrivacy(Model model){return "profile-privacy";}

	@GetMapping("/MyMatch")
	public String profileMyMatch(Model model){return  "profile-MyMatch";}

	@GetMapping("ProfileUpdate")
	public String prifileUpdate(Model model){return "profile-update";}

	//커뮤니티 게시판 동호회원 목록 출력
	@GetMapping("/findClubMemberCno")
	@ResponseBody
	public List<MemberDTOChangHee> findClubMemberCno() {
		int cNo=1;
		return ms.findClubMemberCno(cNo);
	}
}