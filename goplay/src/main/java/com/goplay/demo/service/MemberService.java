package com.goplay.demo.service;

import java.util.List;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dto.MemberDTO;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.Member;

@Service
@Setter
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	public List<Member> findAll(){
		return dao.findAll();
	}
	
	
	public Member findByIdTypeLoc(String id){
		return dao.findByIdTypeLoc(id);
	}

}
