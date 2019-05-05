package com.project.oilpricing.repository;

import java.util.List;

import com.project.oilpricing.model.Oil;

public interface OilRepository {
	List<Oil> listAll();
	Oil findOne(String oilID);
}
