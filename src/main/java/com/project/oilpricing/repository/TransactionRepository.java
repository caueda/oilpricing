package com.project.oilpricing.repository;

import java.util.List;

import com.project.oilpricing.model.Transaction;

public interface TransactionRepository {
	
	String ERR_MESSAGE_OBJECT_ALREADY_EXISTS = "Object already exists.";
	
	List<Transaction> listAll();
	Transaction findOne(Long id);
	Transaction save(Transaction transaction);
	Long getAutoId();
}
