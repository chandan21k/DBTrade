package com.chandan.dbtrade.job;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chandan.dbtrade.constant.TradeConstant;
import com.chandan.dbtrade.model.Trade;
import com.chandan.dbtrade.repository.TradeDao;

@Component
public class JobScheduler {
	
	@Autowired
	private TradeDao tradeDetailsRepository;

	private static final Logger logger = LoggerFactory.getLogger(JobScheduler.class);

	@Scheduled(cron = "0 0 24 * * *")
	public void tradeExpireSchedules() {
		logger.info("update the expire flag if trade crosses the maturity date - Scheduler started");
		List<Trade> tradeDetails = tradeDetailsRepository
				.findByExpiredAndMaturityDateLessThan(TradeConstant.TRADE_EXPIRED_N, LocalDate.now());

		if (null != tradeDetails && tradeDetails.size() != 0) {
			tradeDetails = tradeDetails.stream().map(s -> new Trade(s, TradeConstant.TRADE_EXPIRED_Y))
					.collect(Collectors.toList());

			tradeDetailsRepository.saveAll(tradeDetails);
			logger.info("Total Trades Expired - {}", tradeDetails.size());
		}
		logger.info("update expire flag if trade crosses the maturity date - Scheduler Completed");
	}

}
