
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
	public void addToCart(Product new_product,int amount) {
		for(Product product:products.keySet()) {
			if(new_product==product)
				products.put(product,products.get(product)+amount);
			return;
		}
		products.put(new_product, amount);
	}
	public void removeFromCart(Product new_product,int amount) {
		for(Product product:products.keySet())
			if(new_product==product) {
				products.put(product,products.get(product)-amount);
				return;
			}
	}
	public boolean isEmpty() {
		if(products.size()==0)
			return true;
		return false;
	}
	public void clearCart() {
		products.clear();
	}
	public String listProducts() {
		String productsList=new String();
		for(Product product:products.keySet()) 
			productsList=productsList+product.getName()+",";
		return productsList;
	}
}
