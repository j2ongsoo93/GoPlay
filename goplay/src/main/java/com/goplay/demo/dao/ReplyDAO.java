package com.goplay.demo.dao;

import com.goplay.demo.vo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReplyDAO extends JpaRepository<Reply, Integer> {

}
