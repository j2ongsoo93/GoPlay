package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Reply;

public interface ReplyDAO extends JpaRepository<Reply, Integer> {
	
	
}
