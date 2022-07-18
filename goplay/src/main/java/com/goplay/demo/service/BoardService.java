package com.goplay.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.goplay.demo.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import com.goplay.demo.dao.BoardDAOCustom;
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.vo.Board;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAOCustom daoCustom;

	@Autowired
	private BoardDAO dao;

	//클럽 검색 기능
	public Page<BoardDTO> listBoardSch(Pageable pageable, Integer cNo, @RequestParam @Nullable String thisFirst, @RequestParam @Nullable String thisLast) {
		return daoCustom.listBoardSch(pageable, cNo,thisFirst,thisLast);
	}

	//전체 게시물 띄우기
	public Page<BoardDTO> listBoardAllCno(Pageable pageable, Integer cNo) {
		return daoCustom.listBoardAllCno(pageable, cNo);
	}

	//게시글 작성
	public void saveBoard(Board b) {
		dao.save(b);
	}

	//게시물 삭제
	public void deleteBorad(int bNo) {
		dao.deleteById(bNo);
	}

	public List<BoardDTO> findById(String id){
		return daoCustom.listBoard(id);
	}
}

