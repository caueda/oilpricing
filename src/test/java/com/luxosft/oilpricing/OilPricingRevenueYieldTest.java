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
	public void calcProcessRevenueYieldTest() {
		
		Double price = 12.50;
		
		assertEquals(oilService.processRevenueYield(oilService.findOne("AAC"), price), Double.valueOf(0.08));
		assertEquals(oilService.processRevenueYield(oilService.findOne("REW"), price), Double.valueOf(0.56));
		assertEquals(oilService.processRevenueYield(oilService.findOne("BWO"), price), Double.valueOf(1.36));
		assertEquals(oilService.processRevenueYield(oilService.findOne("TIM"), price), Double.valueOf(0.62));
		assertEquals(oilService.processRevenueYield(oilService.findOne("QFC"), price), Double.valueOf(1.76));
		
		price = 56.34;
		
		assertEquals(oilService.processRevenueYield(oilService.findOne("AAC"), price), Double.valueOf(0.02));
		assertEquals(oilService.processRevenueYield(oilService.findOne("REW"), price), Double.valueOf(0.12));
		assertEquals(oilService.processRevenueYield(oilService.findOne("BWO"), price), Double.valueOf(0.30));
		assertEquals(oilService.processRevenueYield(oilService.findOne("TIM"), price), Double.valueOf(0.14));
		assertEquals(oilService.processRevenueYield(oilService.findOne("QFC"), price), Double.valueOf(0.39));
		
	}
	
	@Test
	public void calcPriceEarningsRationTest() {
		Double price = 12.50;
		
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("AAC"), price), Double.valueOf(156.25));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("REW"), price), Double.valueOf(22.32));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("BWO"), price), Double.valueOf(9.19));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("TIM"), price), Double.valueOf(20.16));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("QFC"), price), Double.valueOf(7.10));
		
		price = 56.34;
		
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("AAC"), price), Double.valueOf(2817));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("REW"), price), Double.valueOf(469.5));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("BWO"), price), Double.valueOf(187.8));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("TIM"), price), Double.valueOf(402.43));
		assertEquals(oilService.processPriceEarningsRation(oilService.findOne("QFC"), price), Double.valueOf(144.46));
		
	}
	
	@Test
	public void listAllTest(){
		assertEquals(oilService.listAll().size(), 5);
	}

	@Test
	public void findOne(){
		assertEquals(oilService.findOne("AAC").getBarrelValue(), Double.valueOf(42));
	}
}
