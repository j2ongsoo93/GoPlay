package com.goplay.demo.service;

import java.util.List;

import com.goplay.demo.dao.MemberDAOCustom;
import com.goplay.demo.dto.MemberDTOChangHee;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.goplay.demo.dao.MemberDAO;
import com.goplay.demo.vo.Member;

@Service
@Setter
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	@Autowired
	private MemberDAO dao;
	@Autowired
	private MemberDAOCustom mCustom;

	public List<Member> findAll(){
		return dao.findAll();
	}


	//회원정보 등록, 수정
	public void saveMember(Member m) {
		validateDuplicateMember(m);
		dao.save(m);
	}
	public Member getById(String id) {
		return dao.getById(id);
	}


//	public List<Member> findById(String id){
//		return dao.findById(id);
//	}

	private void validateDuplicateMember(Member member) {
		Member findid = dao.findById(member.getId());
		Member findphone = dao.findByPhone(member.getPhone());
		Member findemail = dao.findByEmail(member.getEmail());
		Member findnickname = dao.findByNickname(member.getNickname());

		if(findid != null) {
			throw new IllegalStateException("이미 가입된 아이디입니다.");
		}
		else if(findphone != null) {
			throw new IllegalStateException("이미 가입된 핸드폰번호입니다.");
		}
		else if(findemail != null) {
			throw new IllegalStateException("이미 가입된 이메일입니다.");
		}
		else if(findnickname != null) {
			throw new IllegalStateException("이미 가입된 닉네임입니다.");
		}
	}

	public Member findByIdTypeLoc(String id){
		return dao.findByIdTypeLoc(id);
	}

	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
		Member member = dao.findById(id);

		if(member == null) {
			throw new UsernameNotFoundException(id);
		}

		return User.builder()
				.username(member.getId())
				.password(member.getPwd())
				.roles(member.getRole().toString())
				.build();
	}

	public List<MemberDTOChangHee> findClubMemberCno(int cNo){
		return mCustom.findClubMemberCno(cNo);
	}

}

