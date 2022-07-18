package com.goplay.demo.service;

import java.util.List;

import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ClubDAOCustom;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.vo.Club;

import lombok.Setter;

@Service
@Setter
public class ClubService {
	@Autowired
	private ClubDAO dao;
	@Autowired
	private ClubDAOCustom daoCustom;

	//클럽 검색 기능
	public Page<ClubDTO> listClubAll(Pageable pageable,ClubSearchCondition condition) {
		return daoCustom.listClubAll(pageable, condition);
	}

	//추천 동호회(지역, 종목)
	public Page<ClubDTO> listRecommendClub(Pageable pageable, RecommentClubCondition condition) {
		return daoCustom.listRecommendClub(pageable, condition);
	}

	public List<ClubInfoDTO> getClubProfileResult(int cNo) {
		return daoCustom.getClubProfileResult(cNo);
	}


	public List<ClubDTO> listAllClub(){
		return daoCustom.listAllClub();
	}

	//cNo로 클럽 검색
	public List<ClubDTO> findClub(int cNo){
		return daoCustom.findClub(cNo);
	}

}
