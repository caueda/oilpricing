package com.luxosft.oilpricing.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxosft.oilpricing.model.Oil;
import com.luxosft.oilpricing.repository.OilRepository;
import com.luxosft.oilpricing.util.Util;

@Service
public class OilServiceImpl implements OilService {
	
	private OilRepository oilRepository;
	
	@Autowired
	public OilServiceImpl(OilRepository oilRepository){
		this.oilRepository = oilRepository;
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
		
		return Util.round(result);
	}
	
	@Override
	public Double processPriceEarningsRation(final Oil oil, Double price){
		return Util.round(price/processRevenueYield(oil, price));
	}
	
	@Override
	public Collection<Oil> listAll() {
		return oilRepository.listAll();
	}
	
	@Override
	public Oil findOne(String oilID){
		return oilRepository.findOne(oilID);
	}
}
