package com.luxosft.oilpricing.service;

import java.util.Collection;

import com.luxosft.oilpricing.model.Transaction;

public interface TransactionService {

	Double getWeightedOilPriceLastNMinutes(Collection<Transaction> transactions, Integer minutesPassed);
	Collection<Transaction> listAll();
	void save(Transaction transaction);
	Transaction findOne(Long id);
}