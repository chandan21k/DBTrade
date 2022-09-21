package com.chandan.dbtrade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chandan.dbtrade.constant.TradeConstant;
import com.chandan.dbtrade.job.JobScheduler;
import com.chandan.dbtrade.model.Trade;
import com.chandan.dbtrade.repository.TradeDao;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JobSchedulerTest extends DbtradeApplicationTests{

	@InjectMocks
	private JobScheduler jobScheduler;
	
	@Mock
	private TradeDao tradeDao;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public  void setup() {
		MockitoAnnotations.initMocks(jobScheduler); 
	}
	
	@Test
	public void testExpireSchedulesWhenAvailable() {
		Exception expected = null;
		List<Trade> tradeDetails = new ArrayList<>();
		tradeDetails.add(new Trade("T1", 1, "CP-1", "B1", LocalDate.now(), LocalDate.now(), "N"));
		tradeDetails.add(new Trade("T2", 1, "CP-2", "B2", LocalDate.now(), LocalDate.now(), "N"));
		
		Mockito.when(tradeDao
				.findByExpiredAndMaturityDateLessThan(TradeConstant.TRADE_EXPIRED_N, LocalDate.now())).thenReturn(tradeDetails);
		
		Mockito.when(tradeDao.saveAll(tradeDetails)).thenReturn(tradeDetails);
		
		try {
			jobScheduler.tradeExpireSchedules();
		}catch(Exception exp) {
			expected = exp;
		}
		Assert.assertNull(expected);
	}
	
	@Test
	public void testExpireSchedulesWhenNotAvailable() {
		Exception expected = null;
		List<Trade> tradeDetails = null;
		
		Mockito.when(tradeDao
				.findByExpiredAndMaturityDateLessThan(TradeConstant.TRADE_EXPIRED_N, LocalDate.now())).thenReturn(tradeDetails);
		
		
		try {
			jobScheduler.tradeExpireSchedules();
		}catch(Exception exp) {
			expected = exp;
		}
		Assert.assertNull(expected);
	}
	
}
