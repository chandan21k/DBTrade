package com.chandan.dbtrade.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandan.dbtrade.constant.TradeConstant;
import com.chandan.dbtrade.dto.TradeDto;
import com.chandan.dbtrade.model.Trade;
import com.chandan.dbtrade.repository.TradeDao;
import com.chandan.dbtrade.util.TradeValidator;

@Service("tradeServiceImpl")
public class TradeServiceImpl implements TradeService{
	
	
	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private TradeValidator validateTrade;

	@Override
	public void execute(TradeDto tradeRequest) {
		Trade trade = new Trade();
		validateTrade.tradePreValidation(tradeRequest);
		BeanUtils.copyProperties(tradeRequest, trade);
		trade.setCreatedDate(LocalDate.now());
		trade.setTradeExpired(TradeConstant.TRADE_EXPIRED_N);
		trade = tradeDao.save(trade);
	}

}
