package com.goplay.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goplay.demo.vo.MatchBoard;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface MatchBoardDAO extends JpaRepository<MatchBoard, Integer>{
	List<MatchBoard> findAllByOrderByMbDate();
	
	List<MatchBoard> findByMbStat(@Param("mb_stat") String mb_stat);
	
	List<MatchBoard> findByMbStatAndMbType(@Param("mb_stat") String mb_stat, @Param("mb_type") String mb_type);

	List<MatchBoard> findByMbDateAndMbType(@Nullable Date mbDate, String mbType);
	
	//검색 메서드
	List<MatchBoard> findByMbDateAndMbTypeAndMbLoc1AndMbLoc2AndMbStatOrderByMbDateAsc(@Nullable Date mbDate, @Nullable String mbType, @Nullable String mbLoc1, @Nullable String mbLoc2, @Nullable String mbStat);







}
