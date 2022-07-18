package com.goplay.demo.dao;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.vo.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.goplay.demo.dto.QBoardDTO;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@RequiredArgsConstructor
public class BoardDAOCustom {
    private final JPAQueryFactory queryFactory;
    QBoard Board = QBoard.board;

    //게시물 전체 보기
    public Page<BoardDTO> listBoardAllCno(Pageable pageable, Integer cNo) {
    	QueryResults<BoardDTO> results =
    		queryFactory
                .select(new QBoardDTO(Board.bNo, Board.bTitle, Board.bContent, Board.bImg, Board.bVideo, Board.bFile, Board.bDate, Board.bHit, Board.schDate, Board.schPlace, Board.club.cNo, Board.bType, Board.member.id))
                .from(Board)
                .where(Board.club.cNo.eq(cNo)
				)
					//.orderBy(Board.bDate.desc())
			    .fetchResults();
		List<BoardDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
    }
    
    //SELECT * FROM board WHERE c_no=1 AND SCH_DATE IS NOT NULL and (b_date between '22/05/01' and '22/05/31');
    //행사 일정 띄우기
    public Page<BoardDTO> listBoardSch(Pageable pageable, Integer cNo, String thisFirst, String thisLast) {
		LocalDateTime formatThisFirst=null;
		LocalDateTime formatThisLast=null;
		if (thisFirst !=null){
			thisFirst = thisFirst.substring(0,19);
			thisFirst = thisFirst.replace("T", " ");
			thisFirst = thisFirst.replace("Z", "");
			System.out.println("thisFirst2  " + thisFirst);
		}
		if (thisLast !=null){
			thisLast = thisLast.substring(0,19);
			thisLast = thisLast.replace("T", " ");
			thisLast = thisLast.replace("Z", "");
			System.out.println("thisLast2  " + thisLast);
		}
		try{
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			formatThisFirst = LocalDateTime.parse(thisFirst, dtFormat);
			formatThisLast = LocalDateTime.parse(thisLast, dtFormat);
		}catch (Exception e){
			System.out.println("message 2: " + e.getMessage());
		}
    	QueryResults<BoardDTO> results = 
    		queryFactory
                .select(new QBoardDTO(Board.bNo, Board.bTitle, Board.bContent, Board.bImg, Board.bVideo, Board.bFile, Board.bDate, Board.bHit, Board.schDate, Board.schPlace, Board.club.cNo, Board.bType, Board.member.id))
                .from(Board)
                .where(Board.club.cNo.eq(cNo)
                .and(Board.schDate.isNotNull())
								//.and(mbBdateBetween(formatThisFirst, formatThisLast))
								.and(mbFormatThisFirstGOE(formatThisFirst))
								.and(mbFormatThisFirstloe(formatThisLast))

				)
			    .fetchResults();
		List<BoardDTO> content = results.getResults();
		long total = results.getTotal();
		return new PageImpl<>(content, pageable, total);
    
    }

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

	private BooleanExpression mbFormatThisFirstGOE(LocalDateTime formatThisFirst){
		if(formatThisFirst==null ){
			return null;
		}
		return Board.schDate.goe(formatThisFirst);
	}
	private BooleanExpression mbFormatThisFirstloe(LocalDateTime formatThisLast){
		if(formatThisLast==null  ){
			return null;
		}
		return Board.schDate.loe(formatThisLast);
	}


}
