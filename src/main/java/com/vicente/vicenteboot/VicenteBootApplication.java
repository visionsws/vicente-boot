package com.vicente.vicenteboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vicente.vicenteboot.mapper")
public class VicenteBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VicenteBootApplication.class, args);
	}
}
