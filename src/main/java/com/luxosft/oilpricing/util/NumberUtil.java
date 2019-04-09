package com.luxosft.oilpricing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class NumberUtil {
	/**
	 * This method will round the number to two decimal values.
	 * @param value
	 * @return
	 */
	public static Double round(Double value){
		BigDecimal v = new BigDecimal(value);
		return (v.setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
	}
}
