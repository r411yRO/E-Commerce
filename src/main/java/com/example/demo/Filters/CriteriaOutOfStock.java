package com.example.demo.Filters;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Product;

public class CriteriaOutOfStock implements Criteria {

	@Override
	public List<Product> meetCriteria(List<Product> products) {
		List<Product> productsOutOfStock=new ArrayList<>();
		for(Product product:products) {
			if(!product.isInStock())
				productsOutOfStock.add(product);
		}
		return productsOutOfStock;
	}

}
