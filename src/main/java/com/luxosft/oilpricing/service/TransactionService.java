package com.luxosft.oilpricing.service;

import java.util.Collection;

import com.luxosft.oilpricing.domain.Transaction;

public interface TransactionService {

	Double getWeightedOilPriceLastNMinutes(Collection<Transaction> transactions, Integer minutesPassed);

}