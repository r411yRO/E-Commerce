
package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ReviewRepository reviewRepository;

	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}

	@PostMapping("/addProduct")
	public String register(@ModelAttribute Product product, Model model) {
		productRepository.save(product);
		model.addAttribute("products", productRepository.findAll());
		return "addProduct";
	}

	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addCategory";
	}

	@PostMapping("/addCategory")
	public String addCategory(@ModelAttribute Category category, Model model) {
		categoryRepository.save(category);
		model.addAttribute("categories", categoryRepository.findAll());
		return "addCategory";
	}

	@GetMapping("/addReview")
	public String addReview(Model model) {
		model.addAttribute("review", new Review());
		return "addReview";
	}

	@PostMapping("/addReview")
	public String addReview(@ModelAttribute Review review, Model model) {
		reviewRepository.save(review);
		model.addAttribute("reviews", reviewRepository.findAll());
		return "addReview";
	}

	@GetMapping("/products")
	public String productList(Model model) {
		/*
		 * List<Product> products = createProductList(); model.addAttribute("products",
		 * products); return "products";
		 */
		List<Category> categories = createCategoryList();
		model.addAttribute("categories", categories);
		return "productList";
	}

	@GetMapping("/products/{id}")
	public String productDetails(@PathVariable long id, Model model) {
		Optional<Product> product = productRepository.findById(id);
		model.addAttribute("product", product);
		return "productDetails";
	}
	/*
	 * private List<Product> createProductList() { // Creați o listă de produse aici
	 * și returnați-o List<Product> products = new ArrayList<>(); products.add(new
	 * Product("Product 1", 10.99, 5)); products.add(new Product("Product 2", 19.99,
	 * 3)); products.add(new Product("Product 3", 7.50, 10)); return products; }
	 */

	private List<Category> createCategoryList() {
		List<Category> categories = new ArrayList<>();

		Category category1 = new Category("Category 1");
		category1.addProduct(new Product("Product 1", 10.99, 5, "Category1"));
		category1.addProduct(new Product("Product 2", 19.99, 3, "Category1"));

		Category category2 = new Category("Category 2");
		category2.addProduct(new Product("Product 3", 7.50, 10, "Category2"));
		category2.addProduct(new Product("Product 4", 15.49, 8, "Category2"));

		categories.add(category1);
		categories.add(category2);

		return categories;
	}
}
