package com.goplay.demo.dao;

import com.goplay.demo.dto.ClubMemberlistDTO;
import com.goplay.demo.dto.QClubMemberlistDTO;
import com.goplay.demo.vo.QClubMemberlist;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubMemberlistDAO{
    private final JPAQueryFactory queryFactory;
    QClubMemberlist cml = QClubMemberlist.clubMemberlist;

    public List<ClubMemberlistDTO> listClubMember(int cNo){
        return queryFactory
                .select(new QClubMemberlistDTO(
                        cml.listNo,
                        cml.club.cNo,
                        cml.member.id))
                .from(cml)
                .where(cNoEq(cNo))
                .fetch();
    }

    private BooleanExpression cNoEq(int cNo){return cml.club.cNo.eq(cNo);}
}
