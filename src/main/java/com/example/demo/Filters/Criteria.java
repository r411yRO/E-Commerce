package com.example.demo.Filters;
import java.util.*;

import com.example.demo.Product;
public interface Criteria {
	public List<Product> meetCriteria(List<Product> products);
}
