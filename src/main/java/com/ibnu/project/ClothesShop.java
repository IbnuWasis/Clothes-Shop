package com.ibnu.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class ClothesShop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ClothesShop.class, args);
	}

}
