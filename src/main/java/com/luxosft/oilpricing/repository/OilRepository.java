package com.luxosft.oilpricing.repository;

import java.util.Collection;

import com.luxosft.oilpricing.model.Oil;

public interface OilRepository {
	Collection<Oil> listAll();
	Oil findOne(String oilID);
}
