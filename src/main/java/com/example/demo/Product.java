package com.example.demo;

public class Product {
	private String name;
	private String description;
	private int id;
	private double price;
	private int stock;
	private static int lastId=0;
	public Product() {}
	public Product(String name,double price,int stock) {
		this.name=name;
		this.price=price;
		this.stock=stock;
		this.description="Placeholder";
		this.id=generateId();
	}
	public Product(String name,String description,double price,int stock) {
		this.name=name;
		this.description=description;
		this.price=price;
		this.stock=stock;
		this.id=generateId();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	private int generateId() {
		return ++lastId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void increaseStock(int value) {
		this.stock += value;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", id=" + id + ", price=" + price + ", stock="
				+ stock + "]";
	}
	public double getTotalPrice(int quantity) {
		return this.price*quantity;
	}
	public void addToStock(int quantity) {
		this.stock+=quantity;
	}
	public void removeFromStock(int quantity) {
		this.stock-=quantity;
	}
	public boolean isInStock() {
		if(this.stock>0)
			return true;
		return false;
	}
	
}
