package com.goplay.demo.dao;

import com.goplay.demo.vo.ClubMemberlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMemberlistDAO extends JpaRepository<ClubMemberlist, Integer> {
    List<ClubMemberlist> findAllByClub_cNoOrderByListNoAsc(int cNo);
}
