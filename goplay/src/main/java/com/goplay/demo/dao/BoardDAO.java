package com.goplay.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goplay.demo.vo.Board;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {

//		@Query("select nvl(max(bNo),0)+1 from Board")
//		public int getNextbNo();

		public List<Board> findByMember_IdBoards(String id);
	
}
