package com.example.demo;

import java.util.*;

import jakarta.persistence.*;

//@Table(name = "product_table")
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private double price;
	private int stock;
	@ManyToOne
	private Category category;
	@OneToMany(mappedBy = "evaluatedProduct", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product() {
		this.reviews = new ArrayList<>();
	}

	public Product(String name, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = "Placeholder";
	}

	public Product(String name, double price, int stock, String categoryName) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = "Placeholder";
		this.category.setName(categoryName);
		category.addProduct(this);
	}

	public Product(String name, String description, double price, int stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
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

	public long getId() {
		return id;
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
		return this.price * quantity;
	}

	public void addToStock(int quantity) {
		this.stock += quantity;
	}

	public void removeFromStock(int quantity) {
		this.stock -= quantity;
	}

	public boolean isInStock() {
		if (this.stock > 0)
			return true;
		return false;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void removeReview(Review review) {
		review.setEvaluatedProduct(null);
		reviews.remove(review);
	}
}
