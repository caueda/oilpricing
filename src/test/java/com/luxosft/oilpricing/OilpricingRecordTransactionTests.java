package com.luxosft.oilpricing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxosft.oilpricing.model.Oil;
import com.luxosft.oilpricing.model.Transaction;
import com.luxosft.oilpricing.model.TransactionType;
import com.luxosft.oilpricing.service.OilService;
import com.luxosft.oilpricing.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilpricingRecordTransactionTests {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private OilService oilService;
	
	@Test
	public void recordTransaction() {
		double price = 12.50;
		Oil oil = oilService.loadById("AAC");
		Transaction transaction = new Transaction();
		transaction.setOil(oil);
		transaction.setPrice(price);
		transaction.setQuantity(17L);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.BUY);
		
		transactionService.save(transaction);
		
		/* 
		 * The transaction repository is initially empty.
		 * Test quantity of records saved (must be 1).
		 */
		assertEquals(transactionService.listAll().size(), 1);
		
		Transaction load = transactionService.findOne(1L);
		/*
		 * The same instance of Transaction created here, is inserted in a MAP.
		 */
		assertSame(load, transaction);
	}
}

