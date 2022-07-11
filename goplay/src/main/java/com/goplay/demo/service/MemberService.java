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
	
	//회원목록
	public List<Member> listMember(){
		return dao.findAll();
	}
	
<<<<<<< HEAD
	
	public Member findByIdTypeLoc(String id){
		return dao.findByIdTypeLoc(id);
	}

=======
	//회원정보 등록, 수정
	public void saveMember(Member m) {
		dao.save(m);
	}
	
	
	public Member getOne(String id) {
		return dao.getOne(id);
	}
	
//	public List<Member> findById(String id){
//		return dao.findById(id);
//	}
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
}
