package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ClubDAO;
import com.goplay.demo.dao.ClubDAOCustom;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.Club;
import com.querydsl.core.Tuple;

import lombok.Setter;

@Service
@Setter
public class ClubService {

	@Autowired
	private ClubDAOCustom daoCustom;
	
	//클럽 검색 기능
	public Page<ClubDTO> listClubAll(Pageable pageable,ClubSearchCondition condition) {
		return daoCustom.listClubAll(pageable, condition);
	}

	
	public Page<ClubDTO> listRecommendClub(Pageable pageable, RecommentClubCondition condition) {
		return daoCustom.listRecommendClub(pageable, condition);
	}
}
