package com.luxosft.oilpricing.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luxosft.oilpricing.model.Transaction;
import com.luxosft.oilpricing.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository){
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public Double getWeightedOilPriceLastNMinutes(Collection<Transaction> transactions, Integer minutesPassed){
		
		List<Transaction> toProcess = transactionRepository.listAllInLastNMinutes(30);		
		Double zQuantity = toProcess.stream().mapToDouble(Transaction::getQuantity).sum();
		Double zPriceQuantity = toProcess.stream().mapToDouble(Transaction::getQuantityPrice).sum();
		return zPriceQuantity / zQuantity;
		
	}

	@Override
	public void save(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public Collection<Transaction> listAll() {
		return transactionRepository.listAll();
	}

	@Override
	public Transaction findOne(Long id) {
		return transactionRepository.findOne(id);
	}
}
