package com.goplay.demo.dao;

import java.util.List;

import javax.persistence.JoinColumn;

import org.springframework.stereotype.Repository;

import com.goplay.demo.dto.QBoardDTO;
import com.goplay.demo.dto.QReplyDTO;
import com.goplay.demo.dto.ReplyDTO;
import com.goplay.demo.vo.QBoard;
import com.goplay.demo.vo.QReply;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyDAOCustom {
	
	private final JPAQueryFactory queryFactory;
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
