package com.goplay.demo.dao;

import com.goplay.demo.dto.ClubDTO;
import com.goplay.demo.dto.MatchBoardDTO;
import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.searchCondition.MatchBoardSearchCondition;
import com.goplay.demo.vo.MatchBoard;
import com.goplay.demo.vo.QBoard;
import com.goplay.demo.vo.QMatchBoard;
import com.goplay.demo.vo.QReply;
import com.querydsl.jpa.JPAExpressions;
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
public class ReplyDAOCustom {
    private final JPAQueryFactory queryFactory;
    QReply Reply = QReply.reply;
    QBoard Board = QBoard.board;

    //select * from reply where b_no in(select b_no from board where b_no=3)
    public List<ReplyDTO> listReply(Integer bNo) {
        return queryFactory
                .select(Projections.fields(ReplyDTO.class, Reply.rNo, Reply.rContent, Reply.rDate, Reply.board.bNo, Reply.member.id))
                .from(Reply)
                .where(Reply.board.bNo.in(
                		JPAExpressions.select(Board.bNo).from(Board).where(Board.bNo.eq(bNo))))
                .fetch();
    }
    
//    private BooleanExpression mbAwayClubEq(Integer cNo){
//        if(cNo == null){
//            return null;
//        }
//        return matchBoard.awayClub.eq(cNo);
//    }
    


}


