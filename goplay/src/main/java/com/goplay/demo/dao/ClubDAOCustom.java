package com.goplay.demo.dao;

import java.util.List;

import com.goplay.demo.dto.ClubInfoDTO;
import com.goplay.demo.dto.QClubDTO;
import com.goplay.demo.dto.QMatchBoardDTO;
import com.goplay.demo.searchCondition.RecommentClubCondition;
import com.goplay.demo.vo.QClubMemberlist;
import com.goplay.demo.vo.QMatchRecord;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.QClub;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClubDAOCustom {
	private final JPAQueryFactory queryFactory;
	QClub qClub = QClub.club;
	QClubMemberlist qClubMemberlist = QClubMemberlist.clubMemberlist;
	QMatchRecord qMatchRecord = QMatchRecord.matchRecord;

	//모든 클럽 반환
	public List<ClubDTO> listAllClub(){
		return queryFactory
				.select(new QClubDTO(qClub.cNo, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat))
				.from(qClub)
				.fetch();
	}

	//클럽 커뮤니티에 해당 클럽 정보
	public List<ClubInfoDTO> getClubProfileResult(int cNo) {
		return queryFactory
				.select(Projections.constructor(ClubInfoDTO.class, qClub.cNo, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat
						, ExpressionUtils.as(
								JPAExpressions.select(qClubMemberlist.count().longValue()).from(qClubMemberlist)
										.where(qClubMemberlist.club.cNo.eq(cNo)),"memberCount"
						)
						,ExpressionUtils.as(
								JPAExpressions.select(qMatchRecord.win.sum().longValue()).from(qMatchRecord)
										.where(qMatchRecord.club.cNo.eq(cNo)),"win"
						)
						,ExpressionUtils.as(
								JPAExpressions.select(qMatchRecord.draw.sum().longValue()).from(qMatchRecord)
										.where(qMatchRecord.club.cNo.eq(cNo)),"draw"
						)
						,ExpressionUtils.as(
								JPAExpressions.select(qMatchRecord.lose.sum().longValue()).from(qMatchRecord)
										.where(qMatchRecord.club.cNo.eq(cNo)),"lose"
						)
						,ExpressionUtils.as(
								JPAExpressions.select(qMatchRecord.count().longValue()).from(qMatchRecord)
										.where(qMatchRecord.club.cNo.eq(cNo)),"recordCount"
						)
				))
				.from(qClub)
				.where(qClub.cNo.eq(cNo))
				.fetch();
	}
	//추천 동호회
	public Page<ClubDTO> listRecommendClub(Pageable pageable, RecommentClubCondition condition){
		QueryResults<ClubDTO> results = queryFactory
				.select(new QClubDTO(qClub.cNo, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat))
				.from(qClub)
				.where(
						((cLoc1Eq(condition.getC_loc1()))
								.and(cLoc2Eq(condition.getC_loc2())))
								.and(cTypeEq(condition.getSoccer())
										.or(cTypeEq(condition.getFootsal()))
										.or(cTypeEq(condition.getFootvalleyball()))
										.or(cTypeEq(condition.getBascketball()))

								)
				).offset(pageable.getOffset()).limit(pageable.getPageSize())
				.fetchResults();
		List<ClubDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
	}

	//클럽 검색 기능
	public Page<ClubDTO> listClubAll(Pageable pageable, ClubSearchCondition condition) {
		QueryResults<ClubDTO> results = queryFactory
				.select(new QClubDTO(qClub.cNo, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat))
				.from(qClub)
				.where(
						(cTypeEq(condition.getC_type())
								.and(cLoc1Eq(condition.getC_loc1()))
								.and(cLoc2Eq(condition.getC_loc2()))).and
								((qClub.cLoc1.contains(condition.getC_keyword()))
										.or(qClub.cLoc2.contains(condition.getC_keyword()))
										.or(qClub.cName.contains(condition.getC_keyword()))
										.or(qClub.cType.contains(condition.getC_keyword())))
				).offset(pageable.getOffset()).limit(pageable.getPageSize())
				.fetchResults();

//		System.out.println("cTypeEq " + cTypeEq(condition.getC_type()));
//		System.out.println("cLoc1Eq " + cLoc1Eq(condition.getC_loc1()));
//		System.out.println("cLoc2Eq " + cLoc2Eq(condition.getC_loc2()));
		List<ClubDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
	}

	public List<ClubDTO> findClub(int cNo){
		return queryFactory
				.select(new QClubDTO(qClub.cNo, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat))
				.from(qClub)
				.where(cNoEq(cNo))
				.fetch();
	}

    public List<ClubDTO> findClub(int cNo){
        return queryFactory
                .select(new QClubDTO(qClub.cNo, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat))
                .from(qClub)
                .where(cNoEq(cNo))
                .fetch();
    }


    private BooleanExpression cTypeEq(String cType){
        if(cType == null || cType.equals("")){
            return null;
        }
        return qClub.cType.eq(cType);
    }

    private BooleanExpression cLoc1Eq(String cLoc1){
        if(cLoc1 == null || cLoc1.equals("")){
            return null;
        }
        return qClub.cLoc1.eq(cLoc1);
    }

    private BooleanExpression cLoc2Eq(String cLoc2){
        if(cLoc2 == null || cLoc2.equals("")){
            return null;
        }
        return qClub.cLoc2.eq(cLoc2);
    }

    private BooleanExpression cNoEq(int cNo){
        return qClub.cNo.eq(cNo);
    }

}