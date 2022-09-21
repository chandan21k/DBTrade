package com.chandan.dbtrade.exception;

import com.chandan.dbtrade.constant.ErrorConstantEnum;

public class TradeException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	protected String errorId;
	protected String errorMessage;
	protected String httpStatus;
	protected ErrorConstantEnum errorCode;
	protected Exception exception;
	
	public TradeException() {
		super();
	}

	public TradeException(ErrorConstantEnum errorCode, Exception exception) {
		super();
		this.errorCode = errorCode;
		this.errorId = errorCode.getErrorId();
		this.httpStatus = errorCode.getHttpStatus();
		this.errorMessage = errorCode.getErrorMessage();
		this.exception = exception;
	}
	
	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ErrorConstantEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorConstantEnum errorCode) {
		this.errorCode = errorCode;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}


}

