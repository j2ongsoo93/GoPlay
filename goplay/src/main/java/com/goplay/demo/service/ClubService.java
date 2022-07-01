package com.goplay.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goplay.demo.dao.ClubDAO;

import lombok.Setter;

@Service
@Setter
public class ClubService {

	@Autowired
	private ClubDAO dao;
}
