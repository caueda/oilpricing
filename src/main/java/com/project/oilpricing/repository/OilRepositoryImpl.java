package com.project.oilpricing.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.project.oilpricing.model.Oil;

@Component
@Profile({"test", "default"})
public class OilRepositoryImpl implements OilRepository {
	
	/*
	 * Map that will hold the Oil instances and will be the repository.
	 * map.key = key of the Oil
	 * map.valeu = instance of Oil
	 */
	private static final Map<String, Oil> MAP = new HashMap<String, Oil>();
	
	@Override
	public List<Oil> listAll(){
		return MAP.values().stream().collect(Collectors.toList());
	}
	
	@Override
	public Oil findOne(String oilID){
		return MAP.get(oilID);
	}
}
