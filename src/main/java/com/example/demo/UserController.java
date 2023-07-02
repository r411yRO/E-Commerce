package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model, @RequestParam("confirm_password") String cPass) {
		if (!user.isUsersPassword(cPass)) {
			model.addAttribute("errorMessage", "Passwords do not match");
			return "register";
		}
		userRepository.save(user);
		model.addAttribute("users",userRepository.findAll());
		return "/login";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		User user = userRepository.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return "redirect:/welcome?email=" + email;
		} else {
			model.addAttribute("errorMessage", "Autentificare eșuată. Verificați email-ul și parola introduse.");
			model.addAttribute("user", new User());
			return "/login";
		}
	}

	@GetMapping("/welcome")
	public String welcome(@RequestParam("email") String email, Model model) {
		User user = userRepository.findByEmail(email);
		model.addAttribute("user", user);
		return "welcome";
	}

}
