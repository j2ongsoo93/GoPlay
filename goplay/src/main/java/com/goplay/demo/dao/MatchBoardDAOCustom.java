package com.goplay.demo.dao;

import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.goplay.demo.vo.MatchBoard;
import com.goplay.demo.vo.QMatchBoard;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
<<<<<<< HEAD

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchBoardDAOCustom {
    private final JPAQueryFactory queryFactory;
    QMatchBoard matchBoard = QMatchBoard.matchBoard;

    public Page<MatchBoardDTO> searchMatchBoard(MatchBoardSearchCondition condition, Pageable pageable) {
        List<MatchBoardDTO> content = queryFactory
                .select(new QMatchBoardDTO(
                        matchBoard.mb_no,
                        matchBoard.club.cNo.as("homeClub"),
                        matchBoard.awayClub,
                        matchBoard.mbDate,
                        matchBoard.mbType,
                        matchBoard.mbLoc1,
                        matchBoard.mbLoc2,
                        matchBoard.mbStadium,
                        matchBoard.mbFee,
                        matchBoard.homeUcolor,
                        matchBoard.awayUcolor,
                        matchBoard.homeLevel,
                        matchBoard.awayLevel,
                        matchBoard.homeSay,
                        matchBoard.awaySay,
                        matchBoard.hScore,
                        matchBoard.aScore,
                        matchBoard.mbStat))
                .from(matchBoard)
                .where(mbDateEq(condition.getMbDate()),
                        mbTypeEq(condition.getMbType()),
                        mbLoc1Eq(condition.getMbLoc1()),
                        mbLoc2Eq(condition.getMbLoc2()),
                        mbStatEq(condition.getMbStat()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
<<<<<<< HEAD
=======

        JPAQuery<MatchBoard> countQuery = queryFactory
                .select(matchBoard)
                .from(matchBoard)
                .where(mbDateEq(condition.getMbDate()),
                        mbTypeEq(condition.getMbType()),
                        mbLoc1Eq(condition.getMbLoc1()),
                        mbLoc2Eq(condition.getMbLoc2()),
                        mbStatEq(condition.getMbStat()));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
    }
<<<<<<< HEAD
    
    //select * from match_board where away_club=1 or home_club=1
    public Page<MatchBoardDTO> listMatchCno(Pageable pageable, Integer cNo) {
    	QueryResults<MatchBoardDTO> results = 
    		queryFactory
                .select(Projections.fields(MatchBoardDTO.class, matchBoard.mb_no, matchBoard.club.cNo, matchBoard.awayClub, matchBoard.mbDate, matchBoard.mbType, matchBoard.mbLoc1, matchBoard.mbLoc2, matchBoard.mbStadium, matchBoard.mbFee, matchBoard.homeUcolor, matchBoard.awayUcolor, matchBoard.homeLevel, matchBoard.awayLevel, matchBoard.homeSay, matchBoard.awaySay ,matchBoard.hScore, matchBoard.aScore, matchBoard.mbStat))
                .from(matchBoard)
                .where(mbHomeClubEq(cNo)
                .or(mbAwayClubEq(cNo)))
			    .fetchResults();
		List<MatchBoardDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
    }
    
    private BooleanExpression mbAwayClubEq(Integer cNo){
        if(cNo == null){
            return null;
        }
        return matchBoard.awayClub.eq(cNo);
    }
    
    private BooleanExpression mbHomeClubEq(Integer cNo){
        if(cNo == null){
            return null;
        }
        return matchBoard.club.cNo.eq(cNo);
    }
    
    private BooleanExpression mbDateEq(Date mbDate){
=======

    private BooleanExpression mbDateEq(LocalDateTime mbDate){
>>>>>>> branch 'master' of https://github.com/j2ongsoo93/GoPlay.git
        if(mbDate == null){
            return null;
        }
        return matchBoard.mbDate.eq(mbDate);
    }

    private BooleanExpression mbTypeEq(String mbType){
        if(mbType==null){
            return null;
        }
        return matchBoard.mbType.containsIgnoreCase(mbType);
    }
    private BooleanExpression mbLoc1Eq(String mbLoc1){
        if(mbLoc1==null){
            return null;
        }
        return matchBoard.mbLoc1.containsIgnoreCase(mbLoc1);
    }
    private BooleanExpression mbLoc2Eq(String mbLoc2){
        if(mbLoc2==null){
            return null;
        }
        return matchBoard.mbLoc2.containsIgnoreCase(mbLoc2);
    }
    private BooleanExpression mbStatEq(String mbStat){
        if(mbStat==null){
            return null;
        }
        return matchBoard.mbStat.containsIgnoreCase(mbStat);
    }
}


