package com.luxosft.oilpricing.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.luxosft.oilpricing.model.Oil;
import com.luxosft.oilpricing.repository.OilRepository;
import com.luxosft.oilpricing.util.Util;

@Service
public class OilServiceImpl implements OilService {
	
	private OilRepository oilRepository;
	
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
	public Double calculateGeometricMean(Collection<Oil> oils, Double price){
		Double geometricMean = 1.0;
		for(Oil oil : oils){
			geometricMean *= processPriceEarningsRation(oil, price);
		}
		geometricMean = Math.pow(geometricMean, 1.0 / oils.size());
		
		return Util.round(geometricMean);
	}

	@Override
	public Collection<Oil> listAll() {
		return oilRepository.listAll();
	}
	
	@Override
	public Oil loadById(String oilID){
		return oilRepository.findOne(oilID);
	}
}
