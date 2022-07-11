package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.BoardDAOCustom;
import com.goplay.demo.dao.ClubDAO;
import com.goplay.demo.dao.ClubDAOCustom;
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.Club;
import com.querydsl.core.Tuple;

import lombok.Setter;

@Service
@Setter
public class BoardService {

	@Autowired
	private BoardDAOCustom daoCustom;
	
	//클럽 검색 기능
	public Page<BoardDTO> listBoardSch(Pageable pageable, Integer cNo) {
		return daoCustom.listBoardSch(pageable, cNo);
	}
	
	//전체 게시물 띄우기
	public Page<BoardDTO> listBoardAllCno(Pageable pageable, Integer cNo) {
		return daoCustom.listBoardSch(pageable, cNo);
	}
	
}
