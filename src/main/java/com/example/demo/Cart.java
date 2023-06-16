package com.example.demo;

import java.util.Map;
import java.util.HashMap;

public class Cart {
	private int quantity;
	private Map<Product,Integer> products=new HashMap<Product,Integer>();
	private int totalPrice;
	public Cart(Map<Product,Integer> products) {
		this.products=products;
	}
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
		int totalPrice=0;
		for(Product product:products.keySet())
			totalPrice+=product.getTotalPrice(products.get(product));
		return totalPrice;
	}
	public void addToCart(Product product,int amount) {
		products.put(product, amount);
	}
	public void removeFromCart(Product product,int amount) {
		products.remove(product);
	}
}
