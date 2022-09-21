package com.chandan.dbtrade.service;

import com.chandan.dbtrade.dto.TradeDto;

public interface TradeService {
	
	void execute(TradeDto tradeRequest);
	
}
