package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goplay.demo.vo.Member;

@Repository
public interface MemberDAO extends JpaRepository<Member, String>{
	
	
}
