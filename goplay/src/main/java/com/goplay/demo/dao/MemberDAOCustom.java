package com.goplay.demo.dao;

import com.goplay.demo.dto.*;
import com.goplay.demo.vo.QClub;
import com.goplay.demo.vo.QClubMemberlist;
import com.goplay.demo.vo.QMatchBoard;
import com.goplay.demo.vo.QMember;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class MemberDAOCustom {
    QMember member = QMember.member;
    QClubMemberlist clubMemberlist = QClubMemberlist.clubMemberlist;
    QClub club = QClub.club;

    //select * from member where id in (select id from club_memberList where c_no=1)
    private final JPAQueryFactory queryFactory;
    public List<MemberDTOChangHee> findClubMemberCno(int cNo){
        return queryFactory
                .select(new QMemberDTOChangHee(member.memberid,member.id, member.pwd, member.phone, member.email, member.mName,member.nickname, member.birth_date, member.gender,member.soccer,member.footsal,member.footsal,member.footvalleyball,member.mImg,member.mLoc1,member.mLoc2,member.mStat,member.role.stringValue()))
                .from(member)
                .where(member.id.in(
                        JPAExpressions.select(clubMemberlist.member.id).from(clubMemberlist)
                                .where(club.cNo.eq(cNo))
                ))
                .fetch();
    }

}
