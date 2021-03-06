package com.goplay.demo.dao;
import com.goplay.demo.dto.AddressDistrictDTO;
import com.goplay.demo.dto.QAddressDistrictDTO;
import com.goplay.demo.vo.QAddressDistrict;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressDistrictDAO {
    private final JPAQueryFactory queryFactory;
    QAddressDistrict ad = QAddressDistrict.addressDistrict;

    public List<AddressDistrictDTO> listDistrictByAcNO(int acNo){
        return queryFactory
                .select(new QAddressDistrictDTO(
                        ad.adNo,
                        ad.adName,
                        ad.address_city.acNo))
                .from(ad)
                .where(acNoEq(acNo))
                .fetch();
    }

    public List<AddressDistrictDTO> listDistrict(String acName){
        return queryFactory
                .select(new QAddressDistrictDTO(
                        ad.adNo,
                        ad.adName,
                        ad.address_city.acNo))
                .from(ad)
                .where(acNameEq(acName))
                .fetch();
    }

    private BooleanExpression acNameEq(String acName){
        return ad.address_city.acName.eq(acName);
    }

    private BooleanExpression acNoEq(int acNo){
        return ad.address_city.acNo.eq(acNo);
    }
}
