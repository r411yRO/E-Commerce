package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByName(String productName);

	void deleteByName(String productName);

}
