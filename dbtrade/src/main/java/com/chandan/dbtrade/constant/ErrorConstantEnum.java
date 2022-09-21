package com.chandan.dbtrade.constant;



public enum ErrorConstantEnum {
	ERROR_1001("ERROR_1001", "Application Error", TradeConstant.INTERNAL_SERVER_ERROR),
	ERROR_1002("ERROR_1002", "Trade version is less than current", TradeConstant.PRECONDITION_FAILED),
	ERROR_1003("ERROR_1003", "Trade maturity date is less than today", TradeConstant.PRECONDITION_FAILED),
	ERROR_1004("ERROR_1004", "Trade Validation Failed", TradeConstant.PRECONDITION_FAILED);

	private String errorId;
	private String errorMessage;
	private String httpStatus;

	private ErrorConstantEnum(String errorId, String errorMessage, String httpStatus) {
		this.errorId = errorId;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorId() {
		return errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

}
