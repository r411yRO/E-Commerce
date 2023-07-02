package com.example.demo;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String name;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
	public Category() {}
	public Category(String name) {
		this.name = name;
		this.products = new ArrayList<>();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", id=" + id + "]";
	}

	public void addProduct(Product product) {
		products.add(product);
	}

}
