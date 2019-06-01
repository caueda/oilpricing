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
	
	static String timeConversion(String s) {
		String[] hourParts = s.split(":");
		Integer hour = Integer.valueOf(hourParts[0]) + 12;
		if(hourParts[2].contains("PM")) {
			if(hour < 10) 
				hourParts[0] = "0" + hour;			
			else
				hourParts[0] = hour.toString();
		} else {
			if(hour == 24) hourParts[0] = "00";
		}
		return hourParts[0] + ":" + hourParts[1] + ":" + hourParts[2].substring(0,2);
    }
			
	public static void main(String[] args) {
		System.out.println(timeConversion("07:05:45PM"));
	}
}
