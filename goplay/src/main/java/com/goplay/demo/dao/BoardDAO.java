package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Board;

public interface BoardDAO extends JpaRepository<Board, Integer> {

}
