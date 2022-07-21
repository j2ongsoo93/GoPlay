package com.goplay.demo.dao;
import com.goplay.demo.dto.MatchOfferDTO;
import com.goplay.demo.dto.QMatchOfferDTO;
import com.goplay.demo.vo.QMatchOffer;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchOfferDAOCustom {
    private final JPAQueryFactory queryFactory;
    QMatchOffer mo = QMatchOffer.matchOffer;

    public List<MatchOfferDTO> listMatchOffer(int mbNo){
        return queryFactory
                .select(new QMatchOfferDTO(
                        mo.moNo,
                        mo.match_board.mb_no,
                        mo.club.cNo,
                        mo.moUcolor,
                        mo.moLevel,
                        mo.moSay,
                        mo.moDate))
                .from(mo)
                .where(mbNoEq(mbNo))
                .fetch();
    }

    public List<MatchOfferDTO> moByMoNo(int moNo){
        return queryFactory
                .select(new QMatchOfferDTO(
                        mo.moNo,
                        mo.match_board.mb_no,
                        mo.club.cNo,
                        mo.moUcolor,
                        mo.moLevel,
                        mo.moSay,
                        mo.moDate))
                .from(mo)
                .where(moNoEq(moNo))
                .fetch();
    }

    private BooleanExpression mbNoEq(int mbNo){
        return mo.match_board.mb_no.eq(mbNo);
    }
    private BooleanExpression moNoEq(int moNo){
        return mo.moNo.eq(moNo);
    }
}
