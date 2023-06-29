package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
	@GetMapping("/greeting")
	public String greeting(@RequestParam(defaultValue="Mein Fuhrer") String name,@RequestParam(defaultValue="JAVA")String language) {
		if(language.equals("JAVA"))
			return "Hello "+name;
		return "Language not existing";
	}
	@PostMapping("/addGreeting")
	public Greeting addGreeting(@RequestBody Greeting greeting) {
		return greeting;
	}
}
