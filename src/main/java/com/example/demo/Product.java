package com.example.demo;

import java.util.*;

import jakarta.persistence.*;

//@Table(name = "product_table")
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double price;
	private int stock;
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setImage() {
		this.image = this.name.toLowerCase().replaceAll(" ", "");
	}

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

	public void setId(Long id) {
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
		setImage();
	}

	public Product(String name, double price, int stock, Category category) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.description = "Placeholder";
		this.category = category;
		setImage();
	}

	public Product(String name, String description, double price, int stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = new Category("General");
		setImage();
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

	public Long getId() {
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

	public Review addReview(int rating, String title, String comment) {
		Review review = new Review(this, rating, title, comment);
		reviews.add(review);
		return review;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void removeReview(Review review) {
		review.setEvaluatedProduct(null);
		reviews.remove(review);
	}

	public double getAverageRating() {
		List<Review> reviews = getReviews();
		int totalRating = 0;
		for (Review review : reviews) {
			totalRating += review.getRating();
		}
		if (reviews.size() > 0) {
			return (double) totalRating / reviews.size();
		} else {
			return 0.0;
		}
	}
}
