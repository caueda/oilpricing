package com.luxosft.oilpricing.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luxosft.oilpricing.domain.Oil;
import com.luxosft.oilpricing.domain.Type;
import com.luxosft.oilpricing.util.NumberUtil;

@Service
public class OilServiceImpl implements OilService {
	private static final Map<String, Oil> mapDB = new HashMap<String, Oil>();
	
	static {
		insertOilRegister("AAC", Type.STANDARD, 1.0, null, 42.0);
		insertOilRegister("REW", Type.STANDARD, 7.0, null, 47.0);
		insertOilRegister("BWO", Type.STANDARD, 17.0, null, 61.0);
		insertOilRegister("TIM", Type.PREMIUM, 5.0, 7.0, 111.0);
		insertOilRegister("QFC", Type.STANDARD, 22.0, null, 123.0);
	}
	
	private static void insertOilRegister(String oilID, Type type, Double fixedRevenue, Double variableRevenue, Double barrelValue){
		mapDB.put(oilID, new Oil(oilID, type, fixedRevenue, variableRevenue, barrelValue));
	}
	
	public Collection<Oil> listAll(){
		return mapDB.values();
	}
	
	public Oil loadById(String oilID){
		return mapDB.get(oilID);
	}
	
	@Override
	public Double processRevenueYield(final Oil oil, Double price){
		Double result = null;
		switch(oil.getType()){
			case STANDARD:
				result = oil.getFixedRevenue() / price;
				break;
			case PREMIUM:
				result = ((oil.getVariableRevenue()/100.0) * oil.getBarrelValue()) / price;
				break;
		}
		
		return NumberUtil.round(result);
	}
	
	@Override
	public Double processPriceEarningsRation(final Oil oil, Double price){
		return NumberUtil.round(price/processRevenueYield(oil, price));
	}
	
	@Override
	public Double calculateGeometricMean(Collection<Oil> oils, Double price){
		Double geometricMean = 1.0;
		for(Oil oil : oils){
			geometricMean *= processPriceEarningsRation(oil, price);
		}
		geometricMean = Math.pow(geometricMean, 1.0 / oils.size());
		
		return NumberUtil.round(geometricMean);
	}
}
