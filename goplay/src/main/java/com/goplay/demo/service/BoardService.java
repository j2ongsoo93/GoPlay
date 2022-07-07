package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.BoardDAO;
import com.goplay.demo.dao.BoardDAOCustom;
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.vo.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private BoardDAOCustom daoc;
	
	//다음 게시물 번호 
//	public int getNextbNo() {
//		return dao.getNextbNo();
//	}
	
	//게시물 저장, 수정
	public void saveBoard(Board b) {
		dao.save(b);
	}
	
	//게시물 삭제
	public void deleteBorad(int bNo) {
		dao.deleteById(bNo);
	}
	
	public List<BoardDTO> findById(String id){	
		return daoc.listBoard(id);
	}
}


