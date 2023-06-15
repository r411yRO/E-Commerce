package com.example.demo;

import java.util.List;

public class Order {
	List<Product> products;
	int id;
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	String status;
	@Override
	public String toString() {
		return "Order [products=" + products + ", id=" + id + ", status=" + status + "]";
	}
}
