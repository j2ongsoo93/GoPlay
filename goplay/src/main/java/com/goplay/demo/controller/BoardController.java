package com.goplay.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.service.BoardService;
import com.goplay.demo.service.ClubService;
import com.goplay.demo.service.MatchBoardService;
import com.goplay.demo.service.MemberService;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.Member;
import com.querydsl.core.Tuple;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService bs;
	
	//동호회 커뮤니티 내 클럽 정보
	@GetMapping("/listBoardSch")
	@ResponseBody
	public Page<BoardDTO> listBoardSch(Pageable pageable) {
		int cNo=1;//tiger123  의 Cno
		return bs.listBoardSch(pageable,cNo);
	}
	
	//전체 게시물 띄우기
	@GetMapping("/listBoardAllCno")
	@ResponseBody
	public Page<BoardDTO> listBoardAllCno(Pageable pageable) {
		int cNo=1;//tiger123  의 Cno
		System.out.println("dqwdw " + bs.listBoardAllCno(pageable, cNo).getContent());
		return bs.listBoardAllCno(pageable, cNo);
	}
}
