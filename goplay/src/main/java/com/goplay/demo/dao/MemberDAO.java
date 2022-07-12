package com.goplay.demo.dao;


import java.util.List;
import java.util.Optional;

import com.goplay.demo.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberDAO extends JpaRepository<Member, Long>{
	Member getById(String id);
	Member findById(String id);
	Member findByNickname(String nickname);
	Member findByPhone(String phone);
	Member findByEmail(String email);
	
	
	List<Member> findAll();

}
