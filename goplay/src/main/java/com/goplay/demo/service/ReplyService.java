package com.goplay.demo.service;

import java.util.List;

import com.goplay.demo.dao.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goplay.demo.dao.ReplyDAOCustom;
import com.goplay.demo.dto.ReplyDTO;
import lombok.Setter;

@Service
@Setter
public class ReplyService {

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

	//클럽 검색 기능
	public List<ReplyDTO> listReply(Integer bNo) {
		return daoc.listReply(bNo);
	}
}
