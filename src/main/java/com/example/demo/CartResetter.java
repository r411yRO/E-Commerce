package com.example.demo;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CartResetter {
<<<<<<< HEAD
	/*
=======
>>>>>>> 3d279c548b61ac11bbce81d241f5c18306be921b
	private final CartRepository cartRepository;

    CartResetter(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostConstruct
    public void resetTable() {
        cartRepository.deleteAll();
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> 3d279c548b61ac11bbce81d241f5c18306be921b
}
