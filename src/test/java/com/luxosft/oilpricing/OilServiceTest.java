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
public class OilServiceTest {
	
	private final Double resultExpectedForWeightedOil = new Double(17.4);
	private final Double resultExpectedGeometricMean = new  Double(15.21);
	private final Integer resultExpectedForTransactionsForLast30Minutes = new Integer(3);

	@Autowired
	private TransactionService transactionService;
		
	@MockBean
	private TransactionRepository transactionRepository;
		
	/**
	 * This method will test the filtering of transactions inserted in the last 30 minutes
	 */
	@Test
	public void should_beEqualToResultExpectedForTransactionsForLast30Minutes() {
		
		Mockito.when(transactionRepository.listAll()).thenReturn(Stream.of(
				//This register is more than 30 minutes passed and shouldn't appear in the filtered list
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now().minusMinutes(30)),
				//The register below are recently created, and should appear on the filtered result
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),42L, 50.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
		
		assertEquals(transactionService.listAllInLastNMinutes(30).size(), resultExpectedForTransactionsForLast30Minutes.intValue());
		
	}
	
	@Test
	public void should_beEqualToResultExpectedForWeightedOil() {
		Mockito.when(transactionService.listAll()).thenReturn(Stream.of(
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now()),
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
		
		assertEquals(transactionService.getWeightedOilPriceLastNMinutes(30), resultExpectedForWeightedOil);
		
	}
	
	/**
	 * This method will test the calculation based on the transactions in the list
	 */
	@Test
	public void should_beEqualToResultExpectedGeometricMean() {
		
		Mockito.when(transactionService.listAll()).thenReturn(Stream.of(
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now()),
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
		
		assertEquals(transactionService.calculateGeometricMean(), resultExpectedGeometricMean);
		
	}
}

