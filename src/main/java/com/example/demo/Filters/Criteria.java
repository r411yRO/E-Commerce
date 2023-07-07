package com.example.demo.Filters;
import java.util.List;

import com.example.demo.Product;

public interface Criteria {
	public List<Product> meetCriteria(List<Product> products);
}
