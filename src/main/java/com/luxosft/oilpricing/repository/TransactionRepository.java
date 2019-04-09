package com.luxosft.oilpricing.repository;

import java.util.Collection;
import java.util.List;

import com.luxosft.oilpricing.model.Transaction;

public interface TransactionRepository {
	Collection<Transaction> listAll();
	Transaction findOne(Long id);
	List<Transaction> listAllInLastNMinutes(Integer minutesPassed);
	void save(Transaction transaction);
}
