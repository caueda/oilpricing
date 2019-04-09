package com.luxosft.oilpricing.service;

import java.util.Collection;
import java.util.List;

import com.luxosft.oilpricing.model.Transaction;

public interface TransactionService {

	Double getWeightedOilPriceLastNMinutes(Integer minutesPassed);
	Collection<Transaction> listAll();
	void save(Transaction transaction);
	Transaction findOne(Long id);
	List<Transaction> listAllInLastNMinutes(Integer minutesPassed);
	Double calculateGeometricMean();
}