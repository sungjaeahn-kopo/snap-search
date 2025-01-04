package com.snap_search.lvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap_search.lvalue.model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
}
