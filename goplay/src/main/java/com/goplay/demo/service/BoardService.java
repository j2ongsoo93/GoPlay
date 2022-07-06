package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.BoardDAO;
import com.goplay.demo.vo.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
	//다음 게시물 번호 
//	public int getNextbNo() {
//		return dao.getNextbNo();
//	}
	
	//나의 게시물
	public List<Board> listBoards(String id){
		return dao.findByMember_IdBoards(id);
	}
	
	//게시물 저장, 수정
	public void saveBoard(Board b) {
		dao.save(b);
	}
	
	//게시물 삭제
	public void deleteBorad(int bNo) {
		dao.deleteById(bNo);
	}
}


