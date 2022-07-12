package com.goplay.demo.dao;

<<<<<<< HEAD
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
=======
import java.util.List;

import org.springframework.stereotype.Repository;

import com.goplay.demo.dto.BoardDTO;
import com.goplay.demo.dto.QBoardDTO;
import com.goplay.demo.vo.QBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
>>>>>>> LHS_new

@Repository
@RequiredArgsConstructor
public class BoardDAOCustom {
<<<<<<< HEAD
    private final JPAQueryFactory queryFactory;
    QBoard Board = QBoard.board;
    
    
    
    //게시물 전체 보기
    public Page<BoardDTO> listBoardAllCno(Pageable pageable, Integer cNo) {
    	QueryResults<BoardDTO> results = 
    		queryFactory
                .select(Projections.fields(BoardDTO.class, Board.bNo, Board.bContent,Board.bDate, Board.bFile, Board.bHit, Board.bImg, Board.bTitle,Board.bType,Board.bVideo,Board.schDate,Board.schPlace,Board.club.cNo, Board.member.id))
                .from(Board)
                .where(Board.club.cNo.eq(cNo))
			    .fetchResults();
		List<BoardDTO> content = results.getResults();
		long total = results.getTotal();
		System.out.println("total " + total);
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
    //insert into board(b_no, b_content, b_date, b_file, b_hit, b_img, b_title, b_type, b_video, sch_date, sch_place, c_no, id) 
    //values(4,'hihi4', sysdate, '결산.hwp', 20, '첼시.jpg', '첼시 전 후기', 1, '비디오mp4', null, null, 1, 'tiger123')
//    public void insertBoard() {
//    	queryFactory
//    	.insert(Board)
//    		.columns(Board.bContent, Board.bDate, Board.bFile, Board.bHit, Board.bImg, Board.bTitle, Board.bType, Board.bVideo, Board.schDate, Board.schPlace, Board.club.cNo, Board.member.id)
//    	.values("hihi4", sysdate, '결산.hwp', 20, '첼시.jpg', '첼시 전 후기', 1, '비디오mp4', null, null, 1, 'tiger123')
//    }
//    private BooleanExpression mbAwayClubEq(Integer cNo){
//        if(cNo == null){
//            return null;
//        }
//        return matchBoard.awayClub.eq(cNo);
//    }
//    
}


=======
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
>>>>>>> LHS_new
