package com.goplay.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.MyPageDTO;
import com.goplay.demo.service.BoardService;
import com.goplay.demo.service.ReplyService;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardService bs;

	@Autowired
	private ReplyService rs;

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
		int cNo = 1;//tiger123  의 Cno
		System.out.println("dqwdw " + bs.listBoardAllCno(pageable, cNo).getContent());
		return bs.listBoardAllCno(pageable, cNo);
	}

	@GetMapping("/listBoardByReply/{id}")	//마이페이지 내 댓글 조회
	@ResponseBody
	public MyPageDTO listBoardByReply(@PathVariable String id){
		MyPageDTO mypage = new MyPageDTO();
		mypage.setBoard(bs.findById(id));
		mypage.setReply(rs.findByReplyId(id));
		return mypage;
	}

	@GetMapping("/listBoard/{id}")	//마이페이지 내 게시물 조회
	@ResponseBody
	public List<BoardDTO> listBoard(@PathVariable String id){
		return bs.findById(id);
	}

	@GetMapping("/deleteBoard/{bNo}")
	@ResponseBody
	public String delete(@PathVariable int bNo) {
		bs.deleteBorad(bNo);
		return "";
	}
}
