package com.project.oilpricing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.oilpricing.model.Oil;
import com.project.oilpricing.model.Transaction;
import com.project.oilpricing.model.TransactionType;
import com.project.oilpricing.model.Type;
import com.project.oilpricing.repository.TransactionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
	
	private final Double resultExpectedForWeightedOil = new Double(36.09);
	private final Double resultExpectedGeometricMean = new  Double(20.53);
	private final Integer resultExpectedForTransactionsForLast30Minutes = new Integer(3);

	@Autowired
	private TransactionService transactionService;
		
	@MockBean
	private TransactionRepository transactionRepository;
		
	@Before
	public void setUp() {
		Mockito.when(transactionRepository.listAll()).thenReturn(Stream.of(
				//This register is more than 30 minutes passed and shouldn't appear in the filtered list
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),10L, 12.5, TransactionType.BUY, LocalDateTime.now().minusMinutes(30)),
				//The register below are recently created, and should appear on the filtered result
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),13L, 12.5, TransactionType.SELL, LocalDateTime.now()),
				new Transaction(1L, new Oil("REW", Type.STANDARD, 7.0, null, 47.0),42L, 50.5, TransactionType.SELL, LocalDateTime.now())
		).collect(Collectors.toList()));
	}

	@Test
	public void should_beEqualToResultExpectedForTransactionsForLast30Minutes() {
		assertEquals(transactionService.listAllInLastNMinutes(30).size(), resultExpectedForTransactionsForLast30Minutes.intValue());
	}
	
	@Test
	public void should_beEqualToResultExpectedForWeightedOil() {
		assertEquals(resultExpectedForWeightedOil, transactionService.getWeightedOilPriceLastNMinutes(30));
	}
	
	@Test
	public void should_beEqualToResultExpectedGeometricMean() {
		assertEquals(resultExpectedGeometricMean, transactionService.calculateGeometricMean());
	}
	
	@Test
	public void testIfMethodoSaveIsCalledOnce() {
		Transaction transaction = 
				new Transaction(1L, new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),22L, 22.53, TransactionType.SELL, LocalDateTime.now());
		transactionService.save(transaction);
		Mockito.verify(transactionRepository, times(1)).save(transaction);
	}
	
	@Test
	public void testIfMethodFindOneIsCalledOnce() {
		transactionService.findOne(1L);
		Mockito.verify(transactionRepository, times(1)).findOne(Mockito.anyLong());
	}
	
	@Test
	public void testListAll() {
		assertThat(transactionService.listAll().isEmpty(), is(false));
		verify(transactionRepository).listAll();
	}
}

