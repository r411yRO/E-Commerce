package com.example.demo.Filters;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Product;

public class CriteriaInStock implements Criteria {
	@Override
	public List<Product> meetCriteria(List<Product> products){
		List<Product> productsInStock=new ArrayList<>();
		for(Product product:products) {
			if(product.isInStock())
				productsInStock.add(product);
		}
		return productsInStock;
	}

}