package com.goplay.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.goplay.demo.vo.MatchBoard;

public interface MatchBoardDAO extends JpaRepository<MatchBoard, Integer> {
	public List<MatchBoard> findAllByOrderByMbDate();
	

	public List<MatchBoard> findByMbStat(@Param("mb_stat") String mb_stat);
	
	public List<MatchBoard> findByMbStatAndMbType(@Param("mb_stat") String mb_stat, @Param("mb_type") String mb_type);

}
