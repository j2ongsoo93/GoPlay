package com.goplay.demo.dao;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.goplay.demo.vo.MatchBoard;
import com.goplay.demo.vo.QBoard;
import com.goplay.demo.vo.QMatchBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAOCustom {
    private final JPAQueryFactory queryFactory;
    QBoard Board = QBoard.board;
    public Page<BoardDTO> listBoardAllCno(Pageable pageable, Integer cNo) {
    	QueryResults<BoardDTO> results = 
    		queryFactory
                .select(Projections.fields(BoardDTO.class, Board.bNo, Board.bContent,Board.bDate, Board.bFile, Board.bHit, Board.bImg, Board.bTitle,Board.bType,Board.bVideo,Board.schDate,Board.schPlace,Board.club.cNo, Board.member.id))
                .from(Board)
                .where(Board.club.cNo.eq(cNo))
			    .fetchResults();
		List<BoardDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
    }
    
    
    //SELECT * FROM board WHERE c_no=1 AND SCH_DATE IS NOT NULL;
    //행사 일정 띄우기
    public Page<BoardDTO> listBoardSch(Pageable pageable, Integer cNo) {
    	QueryResults<BoardDTO> results = 
    		queryFactory
                .select(Projections.fields(BoardDTO.class, Board.bNo, Board.bContent,Board.bDate, Board.bFile, Board.bHit, Board.bImg, Board.bTitle,Board.bType,Board.bVideo,Board.schDate,Board.schPlace,Board.club.cNo, Board.member.id))
                .from(Board)
                .where(Board.club.cNo.eq(cNo)
                .and(Board.schDate.isNotNull()))
			    .fetchResults();
		List<BoardDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
    }
    
    
//    private BooleanExpression mbAwayClubEq(Integer cNo){
//        if(cNo == null){
//            return null;
//        }
//        return matchBoard.awayClub.eq(cNo);
//    }
//    
}


