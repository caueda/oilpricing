package com.project.oilpricing.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.oilpricing.model.Oil;
import com.project.oilpricing.repository.OilRepository;
import com.project.oilpricing.util.Util;

@Service
public class OilServiceImpl implements OilService {
	
	private OilRepository oilRepository;
	
	@Autowired
	public OilServiceImpl(OilRepository oilRepository){
		this.oilRepository = oilRepository;
	}
	
	private void validateOilType(final Oil oil) {
		if(oil.getType() == null) {
			throw new IllegalArgumentException(ERR_OIL_TYPE_REQUIRED);
		}
	}
	
	@Override
	public Double processRevenueYield(final Oil oil, Double price){
		Double result = null;
		
		validateOilType(oil);
		
		switch(oil.getType()){
			case STANDARD:
				result = oil.getFixedRevenue() / price;
				break;
			default: // PREMIUM:
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
