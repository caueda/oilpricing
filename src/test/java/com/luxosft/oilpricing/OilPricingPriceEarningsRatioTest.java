package com.luxosft.oilpricing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luxosft.oilpricing.service.OilService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OilPricingPriceEarningsRatioTest {
	
	@Autowired
	private OilService oilService;

	@Test
	public void calcPriceEarningsRationTest() {
		Double price = 12.50;
		
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("AAC"), price), Double.valueOf(156.25));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("REW"), price), Double.valueOf(22.32));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("BWO"), price), Double.valueOf(9.19));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("TIM"), price), Double.valueOf(20.16));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("QFC"), price), Double.valueOf(7.10));
		
		price = 56.34;
		
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("AAC"), price), Double.valueOf(2817));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("REW"), price), Double.valueOf(469.5));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("BWO"), price), Double.valueOf(187.8));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("TIM"), price), Double.valueOf(402.43));
		assertEquals(oilService.processPriceEarningsRation(oilService.loadById("QFC"), price), Double.valueOf(144.46));
		
	}

}
