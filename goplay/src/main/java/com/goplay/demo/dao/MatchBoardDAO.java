package com.goplay.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.goplay.demo.vo.MatchBoard;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchBoardDAO extends JpaRepository<MatchBoard, Integer>{
	List<MatchBoard> findAllByOrderByMbDate();
}
