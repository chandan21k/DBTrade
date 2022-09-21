package com.chandan.dbtrade.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Trade {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String tradeId;
	private Long tradeVersion;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	@CreatedDate
	private LocalDate createdDate;
	private String expired;
	
	
	public Trade() {
		super();
	}
	
	public Trade(String tradeId, Long tradeVersion, String counterPartyId, String bookId, LocalDate maturityDate,
			String expired) {
		super();
		this.tradeId = tradeId;
		this.tradeVersion = tradeVersion;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.expired = expired;
	}
	
	public Trade(String tradeId, Integer version, String counterPartyId, String bookId, LocalDate maturityDate,
			LocalDate createdDate, String expired) {
		super();
		this.tradeId = tradeId;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}
	public Trade(Trade tradeDetails, String expired) {
		super();
		this.tradeId = tradeDetails.getTradeId();
		this.tradeVersion = tradeDetails.getTradeVersion();
		this.counterPartyId = tradeDetails.getCounterPartyId();
		this.bookId = tradeDetails.getBookId();
		this.maturityDate = tradeDetails.getMaturityDate();
		this.createdDate = tradeDetails.getCreatedDate();
		this.expired = expired;
		this.id = tradeDetails.getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Long getTradeVersion() {
		return tradeVersion;
	}
	public void setTradeVersion(Long tradeVersion) {
		this.tradeVersion = tradeVersion;
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
	
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String isTradeExpired() {
		return expired;
	}
	public void setTradeExpired(String isExpired) {
		this.expired = isExpired;
	}
	

}
