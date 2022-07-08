package com.goplay.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.vo.Club;
import com.querydsl.core.Tuple;

public interface ClubDAO extends JpaRepository<Club, Integer> {

//	@Query("select soccer,footsal,footvalleyball,bascketball,mLoc1,mLoc2 from Member where id=:id")
//	String findByIdTypeLoc(String id);
	
//	select * from club 
//	where (c_loc1='인천광역시' and c_loc2='계양구')
//	and (c_type='축구' OR c_type='풋살' or c_type='족구' or c_type='농구')
	
	
//	@Query("select c from Club c where (c.cLoc1=:cLoc1 and c.cLoc2=:cLoc2) and (c.cType=:soccer or c.cType=:footsal or c.cType=:footvalleyball or c.cType=:bascketball)")
//	List<Tuple> listRecommendClub(@Param("soccer") String soccer, @Param("footsal") String footsal,
//			@Param("footvalleyball") String footvalleyball,  @Param("bascketball") String bascketball,
//			@Param("cLoc1") String cLoc1, @Param("cLoc2") String cLoc2);
	
	
}
