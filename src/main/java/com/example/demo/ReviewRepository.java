package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review,Long>{

	List<Review> findAllByEvaluatedProduct(Product product);

}

