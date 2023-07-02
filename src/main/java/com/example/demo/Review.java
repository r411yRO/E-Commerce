package com.example.demo;
import java.util.*;
import jakarta.persistence.*;
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	private int rating;
	@ManyToOne
    @JoinColumn(name = "product_id")
	private Product evaluatedProduct;
	private String comment;
	public Review() {}
	public Review(Product product,int rating) {
		this.rating=rating;
		this.evaluatedProduct=product;
	}
	public Review(Product product,int rating,String comment) {
		this.rating=rating;
		this.evaluatedProduct=product;
		this.comment=comment;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getId() {
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
