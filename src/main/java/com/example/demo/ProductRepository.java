package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
 interface ProductRepository extends JpaRepository<Product,Long>{
	
}
