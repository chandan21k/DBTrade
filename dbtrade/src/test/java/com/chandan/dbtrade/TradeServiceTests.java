package com.chandan.dbtrade;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.chandan.dbtrade.dto.TradeDto;
import com.chandan.dbtrade.model.Trade;
import com.chandan.dbtrade.repository.TradeDao;
import com.chandan.dbtrade.service.TradeServiceImpl;
import com.chandan.dbtrade.util.TradeValidator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TradeServiceTests extends DbtradeApplicationTests {
	
	@InjectMocks
	private TradeServiceImpl tradeServiceImpl;
	
	@Mock
	private TradeDao tradeDao;

	@Mock
	private TradeValidator tradeValidator;
	
	
	@BeforeAll
	public  void setup() {
		MockitoAnnotations.initMocks(tradeServiceImpl); 
	}
	
	private Exception actualException=null;
	

	
	@Test
	public void testDoTrade() {
		Trade  trade = null;
		TradeDto tradeDto = new TradeDto("T1", 1, "CP-1", "B1", LocalDate.now());
		
		Mockito.doNothing().when(tradeValidator).tradePreValidation(tradeDto);
		
		Mockito.when(tradeDao.save(trade)).thenReturn(trade);
		try {
		tradeServiceImpl.execute(tradeDto);;
		}catch(Exception e) {
			actualException=e;
		}
		
		Assert.assertEquals(null, actualException);
	}
	
	
}
