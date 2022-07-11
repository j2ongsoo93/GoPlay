package com.goplay.demo.dao;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.goplay.demo.dto.MemberDTO;
import com.goplay.demo.vo.AddressDistrict;
import com.goplay.demo.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member, String>{

	
//	@Query("select soccer, footsal, footvalleyball, bascketball, mLoc1, mLoc2 from Member where id=:id")
//	String findByIdTypeLoc(@Param("id") String id);
	
	@Query("select m from Member m where m.id=:id")
	Member findByIdTypeLoc(@Param("id") String id);
	
}
