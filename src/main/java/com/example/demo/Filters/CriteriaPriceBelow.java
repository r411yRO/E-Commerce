package com.example.demo.Filters;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Product;

public class CriteriaPriceBelow implements Criteria {
	private int price;

	public CriteriaPriceBelow(int price) {
		this.price = price;
	}

	@Override
	public List<Product> meetCriteria(List<Product> products) {
		List<Product> productsInPrice = new ArrayList<>();
		for (Product product : products) {
			if (product.getPrice()<price)
				productsInPrice.add(product);
		}
		return productsInPrice;
	}

}
