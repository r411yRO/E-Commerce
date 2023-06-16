package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
@SpringBootTest
class ECommerceApplicationTests {
	private Cart cart;
	private Product product;	
	public void setUp() {
		List<Product> products=new ArrayList<>();
		products.add(new Product("Laptop",3500,100));
		products.add(new Product("PC",1600,24));
		products.add(new Product("PS5",2500,5));
		Map<Product,Integer> list=new HashMap<Product,Integer>();
		for(Product product:products) {
			list.put(product, 1);
		}
		cart=new Cart(list);
	}
	@Test
	void contextLoads() {
		setUp();
		System.out.println(cart.getTotalPrice());
		assertEquals(cart.getTotalPrice(),7600);
	}

}
