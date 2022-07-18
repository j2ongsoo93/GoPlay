package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Club;

public interface ClubDAO extends JpaRepository<Club, Integer> {

    
}
