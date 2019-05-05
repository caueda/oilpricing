package com.project.oilpricing.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Entity for a transaction
 * @author Carlos Roberto Ueda
 *
 */
@Data
@EqualsAndHashCode(callSuper=false, of={"id"})
@ToString(callSuper=false, of={"id","oil","quantity","price", "transactionType", "transactionDate"})
public class Transaction implements Serializable, Comparable<Transaction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5641192484392003412L;
	
	private Long id;
	private Oil oil;
	private Long quantity;
	private Double price;
	private TransactionType transactionType;
	private LocalDateTime transactionDate;
	
	public Transaction(){
		super();
	}
	
	public Transaction(Long id, Oil oil, Long quantity, Double price, TransactionType transactionType,
			LocalDateTime transactionDate) {
		super();
		this.id = id;
		this.oil = oil;
		this.quantity = quantity;
		this.price = price;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}	
	
	public Transaction(Oil oil, LocalDateTime transactionDate) {
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
