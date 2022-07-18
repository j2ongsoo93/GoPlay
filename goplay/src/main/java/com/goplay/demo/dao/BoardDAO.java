package com.goplay.demo.dao;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.vo.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Board;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {

}
