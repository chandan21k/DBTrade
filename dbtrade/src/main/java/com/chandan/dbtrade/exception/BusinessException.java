package com.chandan.dbtrade.exception;

import com.chandan.dbtrade.constant.ErrorConstantEnum;

public class BusinessException extends TradeException{
	
	
	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(ErrorConstantEnum errorCode, Exception exception) {
		super(errorCode, exception);
	}
	
	
}