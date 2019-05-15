package com.project.oilpricing.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.oilpricing.model.Oil;
import com.project.oilpricing.service.OilService;

@RestController
public class OilPriceController {
	
	private OilService oilService;
	
	public OilPriceController(OilService oilService) {
		this.oilService = oilService;
	}
	
	@RequestMapping(value="/list/oil", method = RequestMethod.GET)
	public ResponseEntity<List<Oil>> getOils(){
		return new ResponseEntity<List<Oil>>(oilService.listAll().stream().collect(Collectors.toList()), HttpStatus.OK);
	}
}

