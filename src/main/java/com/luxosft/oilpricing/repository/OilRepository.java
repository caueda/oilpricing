package com.luxosft.oilpricing.repository;

import java.util.List;

import com.luxosft.oilpricing.model.Oil;

public interface OilRepository {
	List<Oil> listAll();
	Oil findOne(String oilID);
}
