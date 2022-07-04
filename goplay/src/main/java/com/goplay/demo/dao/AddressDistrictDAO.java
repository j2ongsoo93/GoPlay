package com.goplay.demo.dao;

import com.goplay.demo.vo.AddressDistrict;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressDistrictDAO extends JpaRepository<AddressDistrict, Integer> {
    @Query("select a from AddressDistrict a where a.address_city.acNo=:acNo")
    List<AddressDistrict> findAllByAcNo(@Param("acNo") int acNo);
}

