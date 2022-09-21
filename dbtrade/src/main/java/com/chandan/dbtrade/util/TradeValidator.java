package com.chandan.dbtrade.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chandan.dbtrade.constant.ErrorConstantEnum;
import com.chandan.dbtrade.dto.TradeDto;
import com.chandan.dbtrade.exception.BusinessException;
import com.chandan.dbtrade.model.Trade;
import com.chandan.dbtrade.repository.TradeDao;

@Component
public class TradeValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(TradeValidator.class);
	
	@Autowired
	private TradeDao tradeDao;
	
	public void tradePreValidation(TradeDto tradeRequest) {
		Map<ErrorConstantEnum, String> validationErrors = new HashMap<>(0);

		this.validateMaturityDate(tradeRequest.getMaturityDate(), validationErrors, tradeRequest.getTradeId());
		this.validateTradeVersion(tradeRequest.getVersion(), validationErrors, tradeRequest.getTradeId());
		if (!validationErrors.isEmpty()) {
			logger.info("Trade Pre Validation Failed for trade {}", tradeRequest.getTradeId());
			throw new BusinessException(ErrorConstantEnum.ERROR_1004, new RuntimeException("Trade Validations Failed"));
		}
	}

	private void validateMaturityDate(LocalDate maturityDate, Map<ErrorConstantEnum, String> validationErrors, String tradeId) {
		if (maturityDate.isBefore(LocalDate.now())) {
			logger.info("Trade maturity date is less than today for trade {}", tradeId);
			validationErrors.put(ErrorConstantEnum.ERROR_1003, ErrorConstantEnum.ERROR_1003.getErrorMessage());
		}

	}

	private void validateTradeVersion(Integer requestedTradeVersion,
			Map<ErrorConstantEnum, String> validationErrors, String tradeId) {

		Trade existingtradeDetail = tradeDao
				.findTradeDetailsByOrderAndLimit(tradeId);
		if(existingtradeDetail!=null &&  existingtradeDetail.getTradeVersion()!=null) {
		if (requestedTradeVersion < existingtradeDetail.getTradeVersion()) {
			logger.info("Trade version is less than current for trade {}", tradeId);
			validationErrors.put(ErrorConstantEnum.ERROR_1002, ErrorConstantEnum.ERROR_1002.getErrorMessage());
		}
		}
	}


}
