package com.luxosft.oilpricing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxosft.oilpricing.model.Oil;
import com.luxosft.oilpricing.model.Transaction;
import com.luxosft.oilpricing.model.TransactionType;
import com.luxosft.oilpricing.model.Type;
import com.luxosft.oilpricing.repository.TransactionRepository;
import com.luxosft.oilpricing.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilPricingTransactionTests {

	@Autowired
	private TransactionService transactionService;
		
	@MockBean
	private TransactionRepository transactionRepository;
	
	/**
	 * This method will test the persistence of a transaction
	 */
	public void saveAndFindTransactionTest(){
		Transaction transaction = new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now().minusMinutes(40));
		transactionService.save(transaction);
		assertEquals(transactionService.listAll().size(), 1);
		org.junit.Assert.assertSame(transactionService.findOne(1L), transaction);
	}
	
	/**
	 * This method will test the filtering of transactions inserted in the last 30 minutes
	 */
	@Test
	public void last30MinutesTransactions() {
		
		Mockito.when(transactionRepository.listAll()).thenReturn(Stream.of(
				//This register is more than 30 minutes passed and shouldn't appear in the filtered list
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now().minusMinutes(40)),
				//The register below are recently created, and should appear on the filtered result
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),42L, 50.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
		
		assertEquals(transactionService.listAllInLastNMinutes(30).size(), 3);
		
	}
	
	/**
	 * This method will test the calculation of the Weighted Oil Price
	 */
	@Test
	public void calcWeightedOilPriceTest() {
		
		Mockito.when(transactionService.listAll()).thenReturn(Stream.of(
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now()),
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
		
		assertEquals(transactionService.getWeightedOilPriceLastNMinutes(30), new Double(17.4));
		
	}
	
	/**
	 * This method will test the calculation based on the transactions in the list
	 */
	@Test
	public void calcGeometricMeanTest() {
		
		Mockito.when(transactionService.listAll()).thenReturn(Stream.of(
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now()),
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
		
		assertEquals(transactionService.calculateGeometricMean(), new Double(15.21));
		
	}
}

