package com.goplay.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ReplyDAO;

import lombok.Setter;

@Service
@Setter
public class ReplyService {
	
	@Autowired
	private ReplyDAO dao;
	
	public void delete(int rNo) {
		dao.deleteById(rNo);
	}
}
