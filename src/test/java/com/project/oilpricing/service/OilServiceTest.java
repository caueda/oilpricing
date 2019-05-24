package com.project.oilpricing.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.project.oilpricing.model.Oil;
import com.project.oilpricing.model.Type;
import com.project.oilpricing.repository.OilRepository;

public class OilServiceTest {
	
	private final Double expectedResultRevenueYieldStandard = Double.valueOf(0.08);
	private final Double resultExpectedRevenueYieldPremium = Double.valueOf(0.62);
	private final Double resultExptectedPriceEarningsRation = Double.valueOf(156.25);
	
	private OilService oilService;
	
	@Mock
	private OilRepository oilRepository;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		oilService = new OilServiceImpl(oilRepository);
		
		Mockito.when(oilRepository.listAll()).thenReturn(Stream.of(
				new Oil("AAC", Type.STANDARD, 1.0, null, 42.0),
				new Oil("REW", Type.STANDARD, 7.0, null, 47.0),
				new Oil("BWO", Type.STANDARD, 17.0, null, 61.0),
				new Oil("TIM", Type.PREMIUM, 5.0, 7.0, 111.0),
				new Oil("QFC", Type.STANDARD, 22.0, null, 123.0)
		).collect(Collectors.toList()));
	}
	
	@Test
	public void whenOilTypeIsNull_thenReturnIllegalArgumentException() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage(OilService.ERR_OIL_TYPE_REQUIRED);
		oilService.processRevenueYield(new Oil("AAC", null, 1.0, null, 42.0), 12.50);
	}
	
	@Test
	public void should_beEqualToExpectedResultRevenueYieldStandard() {
		Double price = 12.50;
		assertEquals(oilService.processRevenueYield(new Oil("AAC", Type.STANDARD, 1.0, null, 42.0), price), expectedResultRevenueYieldStandard);		
	}
	
	@Test
	public void should_beEqualToExpectedResultRevenueYieldPremium() {
		Double price = 12.50;
		assertEquals(oilService.processRevenueYield(new Oil("TIM", Type.PREMIUM, 5.0, 7.0, 111.0), price), resultExpectedRevenueYieldPremium);		
	}
	
	@Test
	public void should_beEqualToResultExptectedPriceEarningsRation() {
		Double price = 12.50;
		assertEquals(oilService.processPriceEarningsRation(new Oil("AAC", Type.STANDARD, 1.0, null, 42.0), price), resultExptectedPriceEarningsRation);
	}
	
	@Test
	public void whenListAllIsInvoked_thenSizeShouldBeEqualFive() {
		assertEquals(5, oilService.listAll().size());
	}
	
	@Test
	public void whenFindOneIsInvoked_thenReturnRegister() {
		Mockito.when(oilRepository.findOne(Mockito.anyString())).thenReturn(new Oil("AAC", Type.STANDARD, 1.0, null, 42.0));
		assertNotNull(oilService.findOne("AAC"));
	}
}

