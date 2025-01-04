package com.snap_search.lvalue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap_search.lvalue.model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

	Country findByCodeAndName(String code, String name);

	/**
	 * League 테이블과 Country 테이블의 조인관계인 id 조회
	 * @param code
	 * @param name
	 * @return
	 */
	Long findIdByCodeAndName(String code, String name);
}
