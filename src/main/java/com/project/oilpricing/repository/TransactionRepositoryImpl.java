package com.project.oilpricing.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.project.oilpricing.model.Transaction;

@Component
@Profile({"test", "default"})
public class TransactionRepositoryImpl implements TransactionRepository {
	
	private static final Map<Long, Transaction> MAP = new HashMap<>();
	
	@Override
	public Transaction save(Transaction transaction) throws IllegalArgumentException {
		if(transaction.getId() == null) {
			transaction.setId(getAutoId());			
		}
		if(!MAP.containsKey(transaction.getId())){
			MAP.put(transaction.getId(), transaction);
		} else {
			throw new IllegalArgumentException(ERR_MESSAGE_OBJECT_ALREADY_EXISTS);
		}
		return transaction;
	}

	@Override
	public Long getAutoId() {
		Long newId = null;
		try {
			newId = Collections.max(MAP.keySet()) + 1;
		} catch (NoSuchElementException e) {
			newId = 1L;
		}
		return newId;
	}
	
	@Override
	public List<Transaction> listAll() {
		return MAP.values().stream().collect(Collectors.toList());
	}

	@Override
	public Transaction findOne(Long id) {
		return MAP.get(id);
	}
}
