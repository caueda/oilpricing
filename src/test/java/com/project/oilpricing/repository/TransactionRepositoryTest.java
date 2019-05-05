package com.project.oilpricing.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.oilpricing.model.Transaction;
import com.project.oilpricing.model.TransactionType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTest {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void setUp() {
		Transaction transaction = new Transaction();
		transaction.setPrice(100.0);
		transaction.setQuantity(10L);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.BUY);
		transactionRepository.save(transaction);
	}
	
	@Test
	public void whenInsertTransactionAlreadyInserted_thenReturnIllegalArgumentException() {
		Transaction transaction = new Transaction();
		transaction.setId(1L);
		transaction.setPrice(100.0);
		transaction.setQuantity(10L);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.BUY);
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage(TransactionRepository.ERR_MESSAGE_OBJECT_ALREADY_EXISTS);
		transactionRepository.save(transaction);
	}
	
	@Test
	public void whenInsertTransactionSuccess_thenReturnTransactionWithExpectedId() {
		Transaction transaction = new Transaction();
		transaction.setPrice(100.0);
		transaction.setQuantity(10L);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.BUY);
		Long expectedId = transactionRepository.getAutoId();
		assertEquals(expectedId, transactionRepository.save(transaction).getId());
	}
	
	@Test
	public void whenFindOneInvoked_thenReturnTransaction() {
		assertNotNull(transactionRepository.findOne(1L));
	}
}

