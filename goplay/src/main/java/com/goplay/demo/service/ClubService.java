package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ClubDAOCustom;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.ClubDTO;

import lombok.Setter;

@Service
@Setter
public class ClubService {


	@Autowired
	private ClubDAOCustom daoCustom;
	
	//클럽 검색 기능
	public List<ClubDTO> searchClub(ClubSearchCondition condition) {
		return daoCustom.searchClub(condition);
	}
}
