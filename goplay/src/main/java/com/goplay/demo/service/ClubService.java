package com.goplay.demo.service;

import com.goplay.demo.dao.ClubDAO;
import com.goplay.demo.vo.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ClubDAOCustom;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.dto.ClubDTO;

import lombok.Setter;

import java.util.List;

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

	//클럽찾기
	public List<ClubDTO> findByCno(int cNo){
		return daoCustom.findClub(cNo);
	}
}
