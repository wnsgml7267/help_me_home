package com.ssafy.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})  // Controller 스캔
public class HelpmehomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpmehomeApplication.class, args);
	}
}
