package com.example.demo;
import java.util.Scanner;
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
	int avg;
	public Scanner in=new Scanner(System.in);
	public void setUp() {
		List<Product> products=new ArrayList<>();
		products.add(new Product("Laptop",3500,100));
		products.add(new Product("PC",1600,24));
		products.add(new Product("PS5",2500,5));
		Map<Product,Integer> list=new HashMap<Product,Integer>();
		List<Review> rev=new ArrayList<>();
		for(Product product:products) {
			//list.put(product, 1);
			rev.add(new Review(product,in.nextInt()));
		}
		cart=new Cart(list);
		avg=rev.get(0).getAverageRating(rev);
	}
	@Test
	void contextLoads() {
		setUp();
		//System.out.println(cart.getTotalPrice());
	}

}
