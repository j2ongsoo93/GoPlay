package com.goplay.demo.dao;

import com.goplay.demo.vo.AddressCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressCityDAO extends JpaRepository<AddressCity, Integer> {
    List<AddressCity> findAllByOrderByAcNo();

    List<AddressCity> findAllByAcName(String acName);
}
