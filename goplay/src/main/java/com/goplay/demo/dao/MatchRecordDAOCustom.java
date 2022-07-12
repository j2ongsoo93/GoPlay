package com.goplay.demo.dao;

import com.goplay.demo.dto.MatchRecordDTO;
import com.goplay.demo.dto.QMatchRecordDTO;
import com.goplay.demo.vo.QMatchBoard;
import com.goplay.demo.vo.QMatchRecord;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchRecordDAOCustom {
    private final JPAQueryFactory queryFactory;
    QMatchRecord mr = QMatchRecord.matchRecord;

    public List<MatchRecordDTO> matchRecord(int cNo){
        return queryFactory
                .select(new QMatchRecordDTO(mr.win.sum(), mr.draw.sum(), mr.lose.sum()))
                .from(mr)
                .where(cNoEq(cNo))
                .fetch();
    }

    private BooleanExpression cNoEq(int cNo){
        return mr.club.cNo.eq(cNo);
    }

}
