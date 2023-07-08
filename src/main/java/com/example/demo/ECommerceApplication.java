package com.example.demo;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class ECommerceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}