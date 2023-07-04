
package com.example.demo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ReviewRepository reviewRepository;

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

	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addProduct";
	}

	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute Product product, @RequestParam("category") long categoryId, Model model) {
		Category category = categoryRepository.getReferenceById(categoryId);
		product.setCategory(category);
		productRepository.save(product);
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addProduct";
	}

	@GetMapping("/addReview")
	public String addReview(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("products", productRepository.findAll());
		return "addReview";
	}
	@PostMapping("/addReview")
	public String addReview(@ModelAttribute Review review, @RequestParam("evaluatedProduct") long evaluatedProduct,
			Model model) {
		Product product = productRepository.getReferenceById(evaluatedProduct);
		product.addReview(review);
		review.setEvaluatedProduct(product);
		reviewRepository.save(review);
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("reviews", reviewRepository.findAll());
		return "addReview";
	}
	@GetMapping("/products")
	public String productList(Model model) {
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return "products";
	}

	@GetMapping("/products/{id}")
	public String productDetails(@PathVariable long id, Model model) {
		Product product = productRepository.getReferenceById(id);
		model.addAttribute("product", product);
		model.addAttribute("review", new Review());
		List<Review> reviews = reviewRepository.findAllByEvaluatedProduct(product);
		model.addAttribute("reviews", reviews);
		return "productDetails";
	}
	@PostMapping("/products/{id}")
	public String addReviewToProduct(@PathVariable("id") long id, @RequestParam("rating") int rating,@RequestParam("comment")String comment, Model model) {
	    Product product = productRepository.getReferenceById(id);
	    Review review=new Review(product,rating,comment);
	    product.addReview(review);
	    review.setEvaluatedProduct(product);
	    reviewRepository.save(review);
	    List<Review> reviews = reviewRepository.findAllByEvaluatedProduct(product);
	    model.addAttribute("review", review);
	    model.addAttribute("product", product);
	    model.addAttribute("reviews", reviews);
	    return "productDetails";
	}
	@GetMapping("/category/{id}")
	public String categoryDetails(@PathVariable long id, Model model) {
	    Category category = categoryRepository.getReferenceById(id);
	    List<Product> products = category.getProducts();
	    model.addAttribute("category", category);
	    model.addAttribute("productsOfCategory", products);
	    return "categoryDetails";
	}
}
