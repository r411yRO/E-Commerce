package com.example.demo;

public class Review {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Product getEvaluatedProduct() {
		return evaluatedProduct;
	}
	public void setEvaluatedProduct(Product evaluatedProduct) {
		this.evaluatedProduct = evaluatedProduct;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	int id;
	int rating;
	Product evaluatedProduct;
	String comment;
}
