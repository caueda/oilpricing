package com.luxosft.oilpricing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxosft.oilpricing.model.Oil;
import com.luxosft.oilpricing.model.Type;
import com.luxosft.oilpricing.repository.OilRepository;
import com.luxosft.oilpricing.service.OilService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilPricingRevenueYieldTest {
	
	@Autowired
	private OilService oilService;
	
	@Mock
	private OilRepository oilRepository;
	
	@Test
	public void calcProcessRevenueYieldTest() {
		/* 
		 * Test case in Spreadsheet - Table-1 (column Revenue Yield)
		 */
		Double price = 12.50;
		Mockito.when(oilRepository.findOne(Mockito.anyString())).thenReturn(
				new Oil("AAC", Type.STANDARD, 1.0, null, 42.0)
		);
		assertEquals(oilService.processRevenueYield(oilService.findOne("AAC"), price), Double.valueOf(0.08));		
	}
	
	@Test
	public void calcPriceEarningsRationTest() {
		/* 
		 * Test case in Spreadsheet - Table-1 (column Revenue Price-Earnings Ratio)
		 */
		
		Double price = 12.50;
		Mockito.when(oilRepository.findOne(Mockito.anyString())).thenReturn(
				new Oil("AAC", Type.STANDARD, 1.0, null, 42.0)
		);
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("AAC"), price), Double.valueOf(156.25));
		
	}
}

