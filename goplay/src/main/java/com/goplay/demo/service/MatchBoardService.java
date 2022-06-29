package com.goplay.demo.service;

import java.util.List;

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
	
	public List<MatchBoard> listMatchboard(){
		return dao.findAllByOrderByMbDate();
	}
	
	public List<MatchBoard> findMatchBoardByMbStat(String mb_stat){
		return dao.findByMbStat(mb_stat);
	}
	
	public List<MatchBoard> findMatchBoard(String mb_stat, String mb_Type){
		return dao.findByMbStatAndMbType(mb_stat, mb_Type);
	}
	
	//매치등록
	public void saveBoard(MatchBoard mb) {
		dao.save(mb);
	}
}
