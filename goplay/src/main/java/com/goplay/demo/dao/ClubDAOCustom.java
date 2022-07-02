package com.goplay.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.goplay.demo.searchCondition.ClubSearchCondition;
import com.goplay.demo.vo.Club;
import com.goplay.demo.vo.ClubDTO;
import com.goplay.demo.vo.QClub;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClubDAOCustom {
	private final JPAQueryFactory queryFactory;
	QClub qClub = QClub.club;
	//클럽 검색 기능
	public List<ClubDTO> searchClub(ClubSearchCondition condition) {
		return queryFactory
		    .select(Projections.constructor(ClubDTO.class, qClub.c_no, qClub.member.id, qClub.cName, qClub.cType, qClub.cLoc1, qClub.cLoc2, qClub.cImg, qClub.cIntro, qClub.cStat))
		    .from(qClub)
		    .where(
		    		(cTypeEq(condition.getC_type())
		    		.and(cLoc1Eq(condition.getC_loc1()))
		    		.and(cLoc2Eq(condition.getC_loc2()))).and
		    		((qClub.cLoc1.contains(condition.getC_keyword()))
		    		.or(qClub.cLoc2.contains(condition.getC_keyword()))
		    		.or(qClub.cName.contains(condition.getC_keyword()))
		    		.or(qClub.cType.contains(condition.getC_keyword())))
		    		)
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
}
