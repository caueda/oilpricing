package com.luxosft.oilpricing.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.luxosft.oilpricing.model.Oil;
import com.luxosft.oilpricing.model.Type;

@Component
public class OilRepositoryImpl implements OilRepository {
	private static final Map<String, Oil> MAP = new HashMap<String, Oil>();
	
	static {
		insertOilRegister("AAC", Type.STANDARD, 1.0, null, 42.0);
		insertOilRegister("REW", Type.STANDARD, 7.0, null, 47.0);
		insertOilRegister("BWO", Type.STANDARD, 17.0, null, 61.0);
		insertOilRegister("TIM", Type.PREMIUM, 5.0, 7.0, 111.0);
		insertOilRegister("QFC", Type.STANDARD, 22.0, null, 123.0);
	}
	
	private static void insertOilRegister(String oilID, Type type, Double fixedRevenue, Double variableRevenue, Double barrelValue){
		MAP.put(oilID, new Oil(oilID, type, fixedRevenue, variableRevenue, barrelValue));
	}
	
	@Override
	public Collection<Oil> listAll(){
		return MAP.values();
	}
	
	@Override
	public Oil findOne(String oilID){
		return MAP.get(oilID);
	}
}
