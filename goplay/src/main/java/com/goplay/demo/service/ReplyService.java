package com.goplay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.BoardDAOCustom;
import com.goplay.demo.dao.ClubDAO;
import com.goplay.demo.dao.ClubDAOCustom;
import com.goplay.demo.dao.ReplyDAOCustom;
import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.Club;
import com.querydsl.core.Tuple;
=======
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ReplyDAO;
import com.goplay.demo.dao.ReplyDAOCustom;
import com.goplay.demo.dto.ReplyDTO;
>>>>>>> LHS_new

import lombok.Setter;

@Service
@Setter
public class ReplyService {
<<<<<<< HEAD

	@Autowired
	private ReplyDAOCustom daoCustom;
	
	//클럽 검색 기능
	public List<ReplyDTO> listReply(Integer bNo) {
		return daoCustom.listReply(bNo);
	}
	
	
	
=======
	
	@Autowired
	private ReplyDAO dao;
	
	@Autowired
	private ReplyDAOCustom daoc;
	
	public void delete(int rNo) {
		dao.deleteById(rNo);
	}
	
	public List<ReplyDTO> findByReplyId(String id){
		return daoc.listReply(id);
	}
>>>>>>> LHS_new
}
