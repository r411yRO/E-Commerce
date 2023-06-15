package com.example.demo;

import java.util.List;

public class Cart {
	int quantity;
	List<Product> products;
	int totalPrice;
	public int getQuantity() {
		return quantity;
	}
	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + ", products=" + products + ", totalPrice=" + totalPrice + "]";
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
