package com.luxosft.oilpricing.service;

import java.util.Collection;

import com.luxosft.oilpricing.domain.Oil;

public interface OilService {
	/**
	 * This method will return the Revenue Yield
	 * @param oil
	 * @param price
	 * @return
	 */
	Double processRevenueYield(Oil oil, Double price);

	/**
	 * This method will return the Price Earnings Ratio
	 * @param oil
	 * @param price
	 * @return
	 */
	Double processPriceEarningsRation(Oil oil, Double price);

	/**
	 * This method will calculate the Geometric Mean
	 * @param oils
	 * @param price
	 * @return
	 */
	Double calculateGeometricMean(Collection<Oil> oils, Double price);
	
	/**
	 * This method will return a Collection of Oil
	 * @return
	 */
	Collection<Oil> listAll();
	
	/**
	 * This method will load the Oil by its ID
	 * @param oilID
	 * @return
	 */
	Oil loadById(String oilID);
}