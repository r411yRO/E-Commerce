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
import com.example.demo.Filters.*;

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
		model.addAttribute("categories", categoryRepository.findAll());
		return "addCategory";
	}

	@PostMapping("/addCategory")
	public String addCategory(@ModelAttribute Category category, Model model,
			@RequestParam(required = false) String categoryName) {
		if (categoryName != null && !categoryName.isEmpty()) {
			categoryRepository.delete(categoryRepository.findByName(categoryName));
			return "redirect:/addCategory";
		}

		categoryRepository.save(category);
		model.addAttribute("categories", categoryRepository.findAll());
		return "addCategory";
	}

	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addProduct";
	}

	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute Product product, @RequestParam("category") Long categoryId,
			@RequestParam(name = "productId", required = false) Long productId, Model model) {
		if (productId != null && productRepository.findById(productId) != null) {
			productRepository.deleteById(productId);
			model.addAttribute("products", productRepository.findAll());
			model.addAttribute("categories", categoryRepository.findAll());
			return "redirect:/addProduct";
		}
		Category category = categoryRepository.getReferenceById(categoryId);
		product.setCategory(category);
		product.setImage();
		productRepository.save(product);
		List<Product> products = productRepository.findAll(); // Obțineți toate produsele după adăugarea noului produs
		model.addAttribute("products", products);
		model.addAttribute("categories", categoryRepository.findAll());
		System.out.println(product.getImage());
		return "addProduct";
	}

	@GetMapping("/addReview")
	public String addReview(Model model) {
		model.addAttribute("review", new Review());
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("reviews", reviewRepository.findAll());
		return "addReview";
	}

	@PostMapping("/addReview")
	public String addReview(@ModelAttribute Review review, @RequestParam("evaluatedProduct") Long evaluatedProduct,
			@RequestParam(name = "reviewId", required = false) Long reviewId, Model model) {
		if (reviewId != null && productRepository.findById(reviewId) != null) {
			reviewRepository.deleteById(reviewId);
			model.addAttribute("products", productRepository.findAll());
			model.addAttribute("reviews", reviewRepository.findAll());
			return "redirect:/addReview";
		}
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
	public String productDetails(@PathVariable Long id, Model model) {
		Product product = productRepository.getReferenceById(id);
		model.addAttribute("product", product);
		model.addAttribute("review", new Review());
		List<Review> reviews = reviewRepository.findAllByEvaluatedProduct(product);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("reviews", reviews);
		return "productDetails";
	}

	@PostMapping("/products/{id}")
	public String addReviewToProduct(@PathVariable("id") Long id, @RequestParam("rating") int rating,
			@RequestParam("title") String title, @RequestParam("comment") String comment, Model model) {
		Product product = productRepository.getReferenceById(id);
		Review review = new Review(product, rating, title, comment);
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
	public String categoryDetails(@PathVariable Long id, Model model) {
		Category category = categoryRepository.getReferenceById(id);
		List<Product> products = category.getProducts();
		model.addAttribute("category", category);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("products", products);
		return "categoryDetails";
	}

	@GetMapping("/filter")
	public String filterProducts(@RequestParam(value = "stockFilter", required = false) String stockFilter,
			@RequestParam(value = "priceFilter", required = false) String priceFilter,
			@RequestParam(name = "categoryId", required = false) Long categoryId, Model model) {
		String redirect;
		List<Product> products;
		if (categoryId != null) {
			products = categoryRepository.getReferenceById(categoryId).getProducts();
			model.addAttribute("category", categoryRepository.getReferenceById(categoryId));
			redirect = "categoryDetails";
		} else {
			products = productRepository.findAll();
			redirect = "products";
		}
		List<Criteria> criteriaList = new ArrayList<>();
		if (stockFilter != null) {
			if (stockFilter.equals("inStock")) {
				criteriaList.add(new CriteriaInStock());
			} else if (stockFilter.equals("outOfStock")) {
				criteriaList.add(new CriteriaOutOfStock());
			}
		}
		if (priceFilter != null) {
			if (priceFilter.equals("<500")) {
				criteriaList.add(new CriteriaPriceBelow(500));
			} else if (priceFilter.equals("<2000")) {
				criteriaList.add(new CriteriaPriceBelow(2000));
			} else if (priceFilter.equals(">2000")) {
				criteriaList.add(new CriteriaPriceAbove(2000));
			}
		}
		List<Product> filteredProducts = applyFilters(products, criteriaList);
		model.addAttribute("products", filteredProducts);
		model.addAttribute("categories", categoryRepository.findAll());
		return redirect;
	}

	@GetMapping("/search")
	public String searchProduct(@RequestParam(name = "keyword", required = false) String word, Model model) {
		List<Product> products = new ArrayList<>();
		Product product = productRepository.findByName(word);
		System.out.println(word);
		if (word != null && product != null)
			products.add(product);
		else
			products = productRepository.findAll();
		model.addAttribute("products", products);
		model.addAttribute("categories", categoryRepository.findAll());
		return "products";
	}

	private List<Product> applyFilters(List<Product> products, List<Criteria> criteriaList) {
		List<Product> filteredProducts = new ArrayList<>(products);
		for (Criteria criteria : criteriaList) {
			filteredProducts = criteria.meetCriteria(filteredProducts);
		}
		return filteredProducts;
	}

}