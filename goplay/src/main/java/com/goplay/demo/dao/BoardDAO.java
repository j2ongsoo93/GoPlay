package com.goplay.demo.dao;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.vo.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Board;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardDAO extends JpaRepository<Board, Integer> {

//    @Query("select new com.goplay.demo.dto.BoardDTO(b.bNo, b.bTitle, b.bContent, b.bImg, b.bVideo, b.bFile, b.bDate, b.bHit, b.schDate, b.schPlace, b.club.cNo, b.bType, b.member.id) from Board b")
//    public List<BoardDTO> findAllDTO();




}
