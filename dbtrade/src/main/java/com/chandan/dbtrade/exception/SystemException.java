package com.chandan.dbtrade.exception;

import com.chandan.dbtrade.constant.ErrorConstantEnum;

public class SystemException extends TradeException{
	
	private static final long serialVersionUID = 1L;
	
	
	public SystemException() {
		super();
	}

	public SystemException(ErrorConstantEnum errorCode, Exception exception) {
		super(errorCode, exception);
	}
}
