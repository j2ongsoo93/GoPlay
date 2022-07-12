package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Member;

public interface MemberDAO extends JpaRepository<Member, String>{

}
