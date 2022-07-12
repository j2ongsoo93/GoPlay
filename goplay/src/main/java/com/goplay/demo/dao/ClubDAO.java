package com.goplay.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goplay.demo.vo.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubDAO extends JpaRepository<Club, Integer> {
    Club findBycNo(int cNo);

}
