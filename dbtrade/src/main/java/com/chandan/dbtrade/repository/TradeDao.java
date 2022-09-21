package com.chandan.dbtrade.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chandan.dbtrade.model.Trade;
@Repository
public interface TradeDao extends JpaRepository<Trade, Integer>{
	
	List<Trade> findByExpiredAndMaturityDateLessThan(String expired, LocalDate today);

	List<Trade> findByTradeId(String tradeId);

	@Query(value = "SELECT * FROM TRADE_DETAILS where TRADE_ID = :tradeId order by VERSION desc limit 1", nativeQuery = true)
	Trade findTradeDetailsByOrderAndLimit(@Param("tradeId") String tradeId);	
}
