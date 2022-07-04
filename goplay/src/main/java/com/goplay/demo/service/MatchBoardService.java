package com.goplay.demo.service;

import java.util.Date;
import java.util.List;

import com.goplay.demo.dao.MatchBoardDAOCustom;
import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.MatchBoardDAO;
import com.goplay.demo.vo.MatchBoard;

import lombok.Setter;
@Service
@Setter
public class MatchBoardService {
	@Autowired
	private MatchBoardDAO dao;
	@Autowired
	private MatchBoardDAOCustom daoCustom;

	//매치검색 초기목록
	public List<MatchBoard> listMatchBoard(){
		return dao.findAllByOrderByMbDate();
	}

	//매치등록,매치수정
	public void saveBoard(MatchBoard mb) {
		dao.save(mb);
	}

	//매치검색
	public List<MatchBoard> searchMatchBoard(MatchBoardSearchCondition condition) {
		return daoCustom.searchMatchBoard(condition);
	}
}
	
