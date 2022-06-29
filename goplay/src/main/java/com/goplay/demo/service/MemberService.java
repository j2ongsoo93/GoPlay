package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	public List<Member> findAll(){
		return dao.findAll();
	}
}
