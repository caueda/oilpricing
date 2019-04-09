package com.luxosft.oilpricing.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Transaction implements Serializable, Comparable<Transaction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5641192484392003412L;
	
	private Oil oil;
	private Long quantity;
	private Double price;
	private TransactionType transactionType;
	private Date transactionDate;
	
	public Transaction(){
		super();
	}
	
	public Transaction(Oil oil, Date transactionDate) {
		super();
		this.oil = oil;
		this.transactionDate = transactionDate;
	}
	
	@Override
	public int compareTo(Transaction trans) {
		return this.transactionDate.compareTo(trans.getTransactionDate());
	}
	
	public Double getQuantityPrice(){
		return price * quantity;
	}
}