package com.luxosft.oilpricing.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.luxosft.oilpricing.model.Transaction;

@Component
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
	public Collection<Transaction> listAll() {
		return MAP.values();
	}

	@Override
	public Transaction findOne(Long id) {
		return MAP.get(id);
	}

	@Override
	public List<Transaction> listAllInLastNMinutes(Integer minutesPassed) {
		List<Transaction> transactions =
		MAP.values().stream().filter(transactionDate -> {
            ChronoUnit.MINUTES.between(LocalDate.now(), LocalDate.now().minus(minutesPassed, ChronoUnit.MINUTES));
        return false;
		}).collect(Collectors.toList());
		
		return transactions;
	}
}
