package com.goplay.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.goplay.demo.dao.MatchBoardDAOCustom;
import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.dto.MatchDateDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.MatchBoardDAO;
import com.goplay.demo.vo.MatchBoard;

import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

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
	public Page<MatchBoardDTO> searchMatchBoard(MatchBoardSearchCondition condition, Pageable pageable) {
		return daoCustom.searchMatchBoard(condition, pageable);
	}

	//내 매치 검색
	public List<MatchBoard> myMatch(String id){
		return dao.myMatch(id);
	}

	// 날짜별 매치 수 조회
	public List<MatchDateDTO> matchDate(){return daoCustom.matchDate();}

	//클럽 번호로 매치 검색
	public Page<MatchBoardDTO> listMatchCno(Pageable pageable, Integer cNo, @RequestParam @Nullable String thisFirst, @RequestParam @Nullable String thisLast) {
		return daoCustom.listMatchCno(pageable, cNo,thisFirst,thisLast);
	}

	//매치번호로 매치 검색
	public List<MatchBoardDTO> findByMbNo(int mbNo){
		return daoCustom.findByMbNo(mbNo);
	}
}
	
