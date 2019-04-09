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
public class OilPricingRevenueYieldTest {
	
	@Autowired
	private OilService oilService;
	
	@Test
	public void test() {
		
		Double price = 12.50;
		
		assertEquals(oilService.processRevenueYield(oilService.loadById("AAC"), price), Double.valueOf(0.08));
		assertEquals(oilService.processRevenueYield(oilService.loadById("REW"), price), Double.valueOf(0.56));
		assertEquals(oilService.processRevenueYield(oilService.loadById("BWO"), price), Double.valueOf(1.36));
		assertEquals(oilService.processRevenueYield(oilService.loadById("TIM"), price), Double.valueOf(0.62));
		assertEquals(oilService.processRevenueYield(oilService.loadById("QFC"), price), Double.valueOf(1.76));
		
		price = 56.34;
		
		assertEquals(oilService.processRevenueYield(oilService.loadById("AAC"), price), Double.valueOf(0.02));
		assertEquals(oilService.processRevenueYield(oilService.loadById("REW"), price), Double.valueOf(0.12));
		assertEquals(oilService.processRevenueYield(oilService.loadById("BWO"), price), Double.valueOf(0.30));
		assertEquals(oilService.processRevenueYield(oilService.loadById("TIM"), price), Double.valueOf(0.14));
		assertEquals(oilService.processRevenueYield(oilService.loadById("QFC"), price), Double.valueOf(0.39));
		
	}

}
