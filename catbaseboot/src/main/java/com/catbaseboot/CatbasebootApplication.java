package com.catbaseboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class CatbasebootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatbasebootApplication.class, args);
	}
}
