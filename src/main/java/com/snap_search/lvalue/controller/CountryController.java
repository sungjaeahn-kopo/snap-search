package com.snap_search.lvalue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snap_search.lvalue.model.Country;
import com.snap_search.lvalue.service.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

	private final CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping("/fetch")
	public List<Country> fetchCountries() {
		return countryService.fetchAndSaveCountries();
	}
}
