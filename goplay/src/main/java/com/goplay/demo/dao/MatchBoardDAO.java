package com.goplay.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.MatchBoard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchBoardDAO extends JpaRepository<MatchBoard, Integer>{
	List<MatchBoard> findAllByOrderByMbDate();

	@Query("SELECT b FROM MatchBoard b " +
			"WHERE b.mb_no IN (SELECT m.match_board.mb_no FROM MatchMember m " +
			"WHERE m.club_memberlist.listNo = (SELECT cm.listNo FROM ClubMemberlist cm " +
			"WHERE cm.member.id = :id))")
	List<MatchBoard> myMatch(@Param("id") String id);
}
