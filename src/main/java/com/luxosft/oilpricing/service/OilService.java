package com.luxosft.oilpricing.service;

import java.util.Collection;

import com.luxosft.oilpricing.model.Oil;

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