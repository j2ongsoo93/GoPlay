package com.goplay.demo.dao;

import com.goplay.demo.vo.MatchOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchOfferDAO extends JpaRepository<MatchOffer, Integer>{

}
