package com.example.demo;

import java.util.Map;

import jakarta.persistence.*;

import java.util.HashMap;

@Entity
public class Cart {
	@Id
	private long id;
	private int quantity;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@ElementCollection
    @CollectionTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
	private Map<Product,Integer> cartProducts=new HashMap<Product,Integer>();
	private int totalPrice;
	public Cart(Map<Product,Integer> cartProducts) {
		this.cartProducts=cartProducts;
		this.quantity=cartProducts.size();
		this.totalPrice=getTotalPrice();
	}
	public Map<Product, Integer> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(Map<Product, Integer> cartProducts) {
		this.cartProducts = cartProducts;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Cart() {
		this.setId(1);
		this.setQuantity(0);
		this.setTotalPrice(0);
	}
	public int getQuantity() {
		int quantity=0;
		for (Integer productQuantity: cartProducts.values()) {
		    quantity+=productQuantity;
		}
		return quantity;
	}
	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + ", cartProducts=" + cartProducts + ", totalPrice=" + totalPrice + "]";
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		int totalPrice=0;
		for(Product product:cartProducts.keySet())
			totalPrice+=product.getTotalPrice(cartProducts.get(product));
		return totalPrice;
	}
	public void addToCart(Product newProduct, int amount) {
	    if (cartProducts.containsKey(newProduct)) {
	        int currentQuantity = cartProducts.get(newProduct);
	        cartProducts.put(newProduct, currentQuantity + amount);
	    } else {
	        cartProducts.put(newProduct, amount);
	    }
	}

	public void removeFromCart(Product new_product,int amount) {
		for(Product product:cartProducts.keySet())
			if(new_product==product) {
				cartProducts.put(product,cartProducts.get(product)-amount);
				return;
			}
	}
	public boolean isEmpty() {
		if(cartProducts.size()==0)
			return true;
		return false;
	}
	public void clearCart() {
		cartProducts.clear();
	}
	public String listCartProducts() {
		String cartProductsList=new String();
		for(Product product:cartProducts.keySet()) 
			cartProductsList=cartProductsList+product.getName()+",";
		return cartProductsList;
	}
}
