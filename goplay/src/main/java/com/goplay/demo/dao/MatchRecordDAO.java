package com.goplay.demo.dao;

import com.goplay.demo.vo.MatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchRecordDAO extends JpaRepository<MatchRecord, Integer>{

}
