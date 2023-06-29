package com.example.demo;
import java.util.*;
public class Review {
	private static int lastId=0;
	private int id;
	private int rating;
	private Product evaluatedProduct;
	private String comment;
	public Review() {}
	public Review(Product product,int rating) {
		this.rating=rating;
		this.evaluatedProduct=product;
		this.id=generateId();
	}
	public Review(Product product,int rating,String comment) {
		this.rating=rating;
		this.evaluatedProduct=product;
		this.comment=comment;
		this.id=generateId();
	}
	public int getId() {
		return id;
	}
	public int getRating() {
		return rating;
	}
	public void giveRating(int rating) {
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
	public void leaveComment(String comment) {
		this.comment = comment;
	}
	private int generateId() {
		return ++lastId;
	}
	public boolean validateRating() {
		if(this.rating>=1 && this.rating<=5)
			return true;
		else
			return false;
	}
	public int getAverageRating(List<Review> reviews) {
		int totalRating=0;
		for(Review review:reviews) 
			totalRating+=review.rating;
		return totalRating/reviews.size();
	}
}
