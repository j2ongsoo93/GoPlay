package com.goplay.demo.service;

import java.util.List;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.dto.MemberDTO;
import com.goplay.demo.vo.Member;

@Service
@Setter
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	public List<Member> findAll(){
		return dao.findAll();
	}
	

	//회원정보 등록, 수정
	public void saveMember(Member m) {
		dao.save(m);
	}
	
	
	public Member getById(String id) {
		return dao.getById(id);
	}
	

//	public List<Member> findById(String id){
//		return dao.findById(id);
//	}
}
