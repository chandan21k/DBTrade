package com.chandan.dbtrade.dto;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TradeDto {
	
	private String tradeId;
	private Integer version;
	private String counterPartyId;
	private String bookId;
	private String expired;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate maturityDate;
	
	public TradeDto() {
		super();
	}
	
	public TradeDto(String tradeId, Integer version, String counterPartyId, String bookId, LocalDate maturityDate) {
		super();
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
	}

	
	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String isTradeExpired() {
		return expired;
	}
	public void setTradeExpired(String isExpired) {
		this.expired = isExpired;
	}	
}