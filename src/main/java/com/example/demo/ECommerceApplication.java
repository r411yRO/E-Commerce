package com.example.demo;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
<<<<<<< HEAD
//@ComponentScan(basePackages = "com.example")
=======
@ComponentScan(basePackages = "com.example")
>>>>>>> 3d279c548b61ac11bbce81d241f5c18306be921b
public class ECommerceApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}