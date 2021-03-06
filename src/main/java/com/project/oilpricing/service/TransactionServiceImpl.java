package com.project.oilpricing.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.oilpricing.model.Transaction;
import com.project.oilpricing.repository.TransactionRepository;
import com.project.oilpricing.util.Util;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository){
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public Double getWeightedOilPriceLastNMinutes(Integer minutesPassed){
		
		List<Transaction> toProcess = listAllInLastNMinutes(minutesPassed);		
		Double sumQuantity = toProcess.stream().mapToDouble(Transaction::getQuantity).sum();
		Double sumPriceQuantity = toProcess.stream().mapToDouble(Transaction::getQuantityPrice).sum();
		
		return Util.round(sumPriceQuantity / sumQuantity);
		
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

	@Override
	public List<Transaction> listAllInLastNMinutes(Integer minutesPassed) {
		LocalDateTime lowerBound = LocalDateTime.now().minusMinutes(minutesPassed);
		List<Transaction> transactions =
		transactionRepository.listAll().stream().filter(trans -> {
			return trans.getTransactionDate().isAfter(lowerBound);
		}).collect(Collectors.toList());
		
		return transactions;
	}
	
	@Override
	public Double calculateGeometricMean(){
		List<Transaction> transactions = transactionRepository.listAll();
		Double geometricMean = 1.0;
		for(Transaction t : transactions){
			geometricMean *= t.getPrice();
		}
		geometricMean = Math.pow(geometricMean, 1.0 / transactions.size());
		
		return Util.round(geometricMean);
	}
}
