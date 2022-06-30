package com.goplay.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.MatchBoardDAO;
import com.goplay.demo.vo.MatchBoard;

import lombok.Setter;
@Service
@Setter
public class MatchBoardService {
	@Autowired
	private MatchBoardDAO dao;
	
	public List<MatchBoard> listMatchBoard(){
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

	//매치검색
	public List<MatchBoard> findMatchBoard(@Nullable Date mbDate, @Nullable String mbType, @Nullable String mbLoc1, @Nullable String mbLoc2, @Nullable String mbStat){
		return dao.findByMbDateAndMbTypeAndMbLoc1AndMbLoc2AndMbStatOrderByMbDateAsc(mbDate, mbType, mbLoc1, mbLoc2, mbStat);
	}
}
