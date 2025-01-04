package com.snap_search.lvalue.service;

import java.util.List;

import com.snap_search.lvalue.model.Country;

public interface CountryService {
	List<Country> fetchAndSaveCountries();
}
