package com.luxosft.oilpricing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	OilPricingPriceEarningsRatioTest.class,
	OilPricingRevenueYieldTest.class,
	OilPricingTransactionTests.class
})
public class TestSuiteClass {

}
