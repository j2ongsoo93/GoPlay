package com.goplay.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.QBoardDTO;
import com.goplay.demo.vo.QBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDAOCustom {
	private final JPAQueryFactory queryFactory;
	QBoard qb = QBoard.board;
	
	public List<BoardDTO> listBoard(String id){
		return queryFactory
				.select(new QBoardDTO(qb.bNo, qb.bTitle, qb.bContent, qb.bImg, qb.bVideo, qb.bFile, qb.bDate, qb.bHit, qb.schDate, qb.schPlace, qb.club.cNo, qb.bType, qb.member.id))
				.from(qb)
				.where(idEq(id))
				.fetch();
	}
	
	private BooleanExpression idEq(String id) {
		return qb.member.id.eq(id);
	}
	
	
}
