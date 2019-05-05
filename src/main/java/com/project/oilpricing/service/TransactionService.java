package com.project.oilpricing.service;

import java.util.Collection;
import java.util.List;

import com.project.oilpricing.model.Transaction;

public interface TransactionService {

	Double getWeightedOilPriceLastNMinutes(Integer minutesPassed);
	Collection<Transaction> listAll();
	void save(Transaction transaction);
	Transaction findOne(Long id);
	List<Transaction> listAllInLastNMinutes(Integer minutesPassed);
	Double calculateGeometricMean();
}