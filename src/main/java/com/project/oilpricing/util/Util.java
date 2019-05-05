package com.project.oilpricing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Util {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	/**
	 * This method will round the number to two decimal values.
	 * @param value
	 * @return
	 */
	public static Double round(Double value){
		BigDecimal v = new BigDecimal(value);
		return (v.setScale(2, RoundingMode.HALF_EVEN)).doubleValue();
	}
	
	public static Date getDate(String str) throws ParseException {
		
		return sdf.parse(str);
	}
}
