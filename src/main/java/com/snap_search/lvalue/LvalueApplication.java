package com.snap_search.lvalue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LvalueApplication {

	public static void main(String[] args) {
		SpringApplication.run(LvalueApplication.class, args);
	}

}
