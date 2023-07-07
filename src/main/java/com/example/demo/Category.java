package com.example.demo;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
	/*
	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;

	@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
	private List<Category> subcategories;
*/
	public Category() {
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", id=" + id + "]";
	}

	public void addProduct(Product product) {
		products.add(product);
	}
	/*
	public void addSubcategory(Category subcategory) {
		if (subcategory.getParentCategory() != null) {
			subcategory.getParentCategory().getSubcategories().remove(subcategory);
		}
		subcategory.setParentCategory(this);
		this.subcategories.add(subcategory);
	}

	public void removeSubcategory(Category subcategory) {
		subcategory.setParentCategory(null);
		this.subcategories.remove(subcategory);
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}*/
}
