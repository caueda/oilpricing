package com.project.oilpricing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OilPriceController {
	@RequestMapping("/prices")
	public String getPrices(Model model){
		List<String> prices = new ArrayList<String>();
		prices.add("10");
		prices.add("20");
		model.addAttribute("prices", prices);
		return "prices";
	}
}

