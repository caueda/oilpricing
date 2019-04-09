package com.luxosft.oilpricing;

import static org.junit.Assert.*;

import java.util.Date;

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
		Date date = new Date();
		Oil oil = oilService.loadById("AAC");
		Transaction transaction = new Transaction();
		transaction.setOil(oil);
		transaction.setPrice(price);
		transaction.setQuantity(17L);
		transaction.setTransactionDate(date);
		transaction.setTransactionType(TransactionType.BUY);
		
		transactionService.save(transaction);
		
		/* 
		 * The transaction repository is initially empty.
		 * Test quantity of records saved (must be 1).
		 */
		assertEquals(transactionService.listAll().size(), 1);
		
		Transaction load = transactionService.findOne(1L);
		/*
		 * Test of the values recorded
		 */
		assertEquals(load.getPrice(), price, 0.0);
		assertEquals(load.getQuantity(), Long.valueOf(17));
		assertEquals(load.getTransactionDate(), date);
		assertEquals(load.getTransactionType(), TransactionType.BUY);
	}

}

