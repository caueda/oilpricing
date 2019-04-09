package com.luxosft.oilpricing.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.luxosft.oilpricing.domain.Oil;
import com.luxosft.oilpricing.domain.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {
	private static final Map<Oil, List<Transaction>> mapTransactions = new HashMap<>();
	
	public Collection<Transaction> listAllByOild(final Oil oil){
		return mapTransactions.get(oil);
	}
	
	public void add(final Transaction transaction){
		if(mapTransactions.get(transaction.getOil()) == null){
			mapTransactions.put(transaction.getOil(), new ArrayList<Transaction>());
		}
		mapTransactions.get(transaction.getOil()).add(transaction);
	}
	
	/* (non-Javadoc)
	 * @see com.luxosft.oilpricing.service.TransactionService#getWeightedOilPriceLastNMinutes(java.util.Collection, java.lang.Integer)
	 */
	@Override
	public Double getWeightedOilPriceLastNMinutes(Collection<Transaction> transactions, Integer minutesPassed){
		List<Transaction> toProcess = 
		transactions.stream().filter(transactionDate -> {
	            ChronoUnit.MINUTES.between(LocalDate.now(), LocalDate.now().minus(minutesPassed, ChronoUnit.MINUTES));
	        return false;
	    }).collect(Collectors.toList());
		
		Double zQuantity = toProcess.stream().mapToDouble(Transaction::getQuantity).sum();
		Double zPriceQuantity = toProcess.stream().mapToDouble(Transaction::getQuantityPrice).sum();
		return zPriceQuantity / zQuantity;
	}
}
