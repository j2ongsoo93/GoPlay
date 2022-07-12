package com.goplay.demo.dao;


import java.util.List;

import com.goplay.demo.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberDAO extends JpaRepository<Member, String>{
	Member getById(String id);
	
	List<Member> findAll();

}
