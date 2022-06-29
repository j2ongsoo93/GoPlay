package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Faq;

public interface FaqDAO extends JpaRepository<Faq, Integer>{
	
}
