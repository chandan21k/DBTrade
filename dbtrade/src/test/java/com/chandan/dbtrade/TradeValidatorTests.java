package com.chandan.dbtrade;

import java.time.LocalDate;

import org.aspectj.apache.bcel.ExceptionConstants;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chandan.dbtrade.constant.ErrorConstantEnum;
import com.chandan.dbtrade.constant.TradeConstant;
import com.chandan.dbtrade.dto.TradeDto;
import com.chandan.dbtrade.exception.BusinessException;
import com.chandan.dbtrade.model.Trade;
import com.chandan.dbtrade.repository.TradeDao;
import com.chandan.dbtrade.util.TradeValidator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class TradeValidatorTests extends DbtradeApplicationTests{

		@InjectMocks
		private TradeValidator tradeValidator;
		
		@Mock
		private TradeDao tradeDao;
		
		@BeforeAll
		public  void setup() {
			MockitoAnnotations.initMocks(tradeValidator); 
		}
		
		@Test
		public void testtradePreValidationInvalidMaturityDate() {
			Trade  trade = null;
			TradeDto tradeRequest = new TradeDto("T1", 1, "CP-1", "B1", LocalDate.now().minusDays(2L));
			
			try {
				Mockito.when(tradeDao.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(trade);
				tradeValidator.tradePreValidation(tradeRequest);
			}catch(BusinessException be ) {
				Assert.assertNotNull(be);
				Assert.assertEquals(ErrorConstantEnum.ERROR_1004, be.getErrorCode());
			}
		}
		
		@Test
		public void testtradePreValidationFutureMaturityDate() {
			Trade  trade = null;
			TradeDto tradeRequest = new TradeDto("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(1L));
			Exception ex = null;
			try {
				Mockito.when(tradeDao.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(trade);
				tradeValidator.tradePreValidation(tradeRequest);
			}catch(Exception tve ) {
				ex = tve;
			}
			Assert.assertNull(ex);
		}
		
		@Test
		public void testtradePreValidationTodayMaturityDate() {
			Trade  trade = null;
			TradeDto tradeRequest = new TradeDto("T1", 1, "CP-1", "B1", LocalDate.now());
			Exception ex = null;
			try {
				Mockito.when(tradeDao.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(trade);
				tradeValidator.tradePreValidation(tradeRequest);
			}catch(Exception tve ) {
				ex = tve;
			}
			Assert.assertNull(ex);
		}
		
		@Test
		public void testvalidateTradeVersionWithSameVersion() {
			Trade  trade = new Trade("T1", 1l,"CP-1", "B1",LocalDate.now().plusDays(1L),TradeConstant.TRADE_EXPIRED_N);
			TradeDto tradeRequest = new TradeDto("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(1L));
			Exception ex = null;
			try {
				Mockito.when(tradeDao.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(trade);
				tradeValidator.tradePreValidation(tradeRequest);
			}catch(Exception tve ) {
				ex = tve;
			}
			Assert.assertNull(ex);
		}
		
		
		@Test
		public void testvalidateTradeVersionWithFewerVersion() {
			Trade  trade = new Trade("T1", 2l,"CP-1", "B1",LocalDate.now().plusDays(1L),TradeConstant.TRADE_EXPIRED_N);
			TradeDto tradeRequest = new TradeDto("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(1L));
			Exception ex = null;
			try {
				Mockito.when(tradeDao.findTradeDetailsByOrderAndLimit(tradeRequest.getTradeId())).thenReturn(trade);
				tradeValidator.tradePreValidation(tradeRequest);
			}catch(BusinessException be ) {
				Assert.assertNotNull(be);
				Assert.assertEquals(ErrorConstantEnum.ERROR_1004, be.getErrorCode());
			}
			
		}
}
