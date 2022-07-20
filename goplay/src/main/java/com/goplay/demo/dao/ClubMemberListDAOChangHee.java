package com.goplay.demo.dao;

import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.ClubMemberlist;
import com.goplay.demo.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClubMemberListDAOChangHee extends JpaRepository<ClubMemberlist, Integer>{

    @Transactional
    @Modifying
    @Query("delete from ClubMemberlist c where c.club.cNo = :cNoChangHee and c.member.id =:currentid")
    int deleteByClubAndMember(int cNoChangHee, String currentid);
}
