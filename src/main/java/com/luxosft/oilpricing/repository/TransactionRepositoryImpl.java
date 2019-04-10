package com.luxosft.oilpricing.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.luxosft.oilpricing.model.Transaction;

@Component
@Profile({"test", "default"})
public class TransactionRepositoryImpl implements TransactionRepository {
	
	private static final AtomicLong SEQUENCE = new AtomicLong();
	
	private static final Map<Long, Transaction> MAP = new HashMap<>();
	
	@Override
	public void save(Transaction transaction){
		transaction.setId(SEQUENCE.incrementAndGet());
		if(!MAP.containsKey(transaction.getId())){
			MAP.put(transaction.getId(), transaction);
		}
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
