package com.goplay.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.MatchBoard;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchBoardDAO extends JpaRepository<MatchBoard, Integer>{
	List<MatchBoard> findAllByOrderByMbDate();
}
