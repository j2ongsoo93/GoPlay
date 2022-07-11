package com.goplay.demo.service;

import com.goplay.demo.dao.ClubDAO;
import com.goplay.demo.vo.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ClubDAO;
import com.goplay.demo.dao.ClubDAOCustom;
<<<<<<< HEAD
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.ClubDTOInterface;
import com.goplay.demo.dto.ClubInfoDTO;
=======
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
import com.goplay.demo.searchCondition.ClubSearchCondition;
<<<<<<< HEAD
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.Club;
import com.querydsl.core.Tuple;
=======
import com.goplay.demo.dto.ClubDTO;
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git

import lombok.Setter;

import java.util.List;

@Service
@Setter
public class ClubService {
<<<<<<< HEAD

=======
	@Autowired
	private ClubDAO dao;
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
	@Autowired
	private ClubDAOCustom daoCustom;
	
	@Autowired
	private ClubDAO dao;
	
	//클럽 검색 기능
	public Page<ClubDTO> listClubAll(Pageable pageable,ClubSearchCondition condition) {
		return daoCustom.listClubAll(pageable, condition);
	}

<<<<<<< HEAD
	//추천 동호회(지역, 종목)
	public Page<ClubDTO> listRecommendClub(Pageable pageable, RecommentClubCondition condition) {
		return daoCustom.listRecommendClub(pageable, condition);
	}
	
	public List<ClubInfoDTO> getClubProfileResult(int cNo) {
		return daoCustom.getClubProfileResult(cNo);
	}
	
	
	public List<ClubDTO> listAllClub(){
		return daoCustom.listAllClub();
=======
	//클럽찾기
	public List<ClubDTO> findByCno(int cNo){
		return daoCustom.findClub(cNo);
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
	}
}
