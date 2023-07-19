package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/cart")
	public String getCart(Model model) {
		if (!cartRepository.existsById((long) 1))
			cartRepository.save(new Cart());
		Cart cart = cartRepository.getReferenceById((long) 1);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("cartProducts", cart.getCartProducts());
		model.addAttribute("title", "Cart");
		return "cart";
	}

	@PostMapping("/addToCart")
	public String addToCart(Model model, @RequestParam(name = "numberOf") int numberOf,
			@RequestParam(name = "productId") long productId) {
		Product product = productRepository.getReferenceById(productId);
		if (!cartRepository.existsById((long) 1))
			cartRepository.save(new Cart());
		Cart cart = cartRepository.getReferenceById((long) 1);
		if (product.isInStock()) {
			cart.addToCart(product, numberOf);
			cart.setQuantity(cart.getQuantity());
			cart.setTotalPrice(cart.getTotalPrice());
			cartRepository.save(cart);
		}
		model.addAttribute("product", product);
		model.addAttribute("cart", cart);
		return "redirect:/products";
	}

	@GetMapping("/purchase")
	public String purchase(Model model) {
		Cart cart = cartRepository.getReferenceById((long) 1);
		for (Map.Entry<Product, Integer> cartProduct:cart.getCartProducts().entrySet()){
			cartProduct.getKey().removeFromStock(cartProduct.getValue());
			productRepository.save(cartProduct.getKey());
		}
		cartRepository.deleteAll();
		return "purchase-succes";
	}
}
