package com.goplay.demo.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.goplay.demo.vo.Member;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface MemberDAO extends JpaRepository<Member, Long>{
	Member getById(String id);
	List<Member> findAll();
	Member findById(String id);
	Member findByEmail(String email);
	Member findByNickname(String nickname);
	Member findByPhone(String phone);

	/*@Query("UPDATE Member m set m.nickname=:nickname where m.id=:id")
	Member update(@RequestBody HashMap<String, String> map, Model model );*/

	@Query("select m from Member m where m.id=:id")
	Member findByIdTypeLoc(@Param("id") String id);

	@Query("select m from Member m where m.id = ?1 order by m.phone DESC")
	List<Member> findByIdOrderByPhoneDesc(String id);

}
