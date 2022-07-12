package com.goplay.demo.dao;

import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.vo.QBoard;
import com.goplay.demo.vo.QReply;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.goplay.demo.dto.QReplyDTO;

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

	QReply qr = QReply.reply;
	QBoard qb = QBoard.board;

	public List<ReplyDTO> listReply(String id){
		return queryFactory
				.select(new QReplyDTO(qr.rNo, qr.board.bNo, qr.rContent, qr.rDate, qr.member.id))
				.from(qr)
				.where(idEq(id))
				.fetch();
	}

	private BooleanExpression idEq(String id) {
		return qr.member.id.eq(id);
	}
}
