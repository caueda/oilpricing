package com.luxosft.oilpricing.repository;

import java.util.List;

import com.luxosft.oilpricing.model.Transaction;

public interface TransactionRepository {
	List<Transaction> listAll();
	Transaction findOne(Long id);
	void save(Transaction transaction);
}
