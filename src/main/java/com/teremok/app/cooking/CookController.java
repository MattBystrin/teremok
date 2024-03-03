package com.teremok.app.cooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cooking")
@RequiredArgsConstructor
public class CookController {

	private final CookService cookService;

	@PostMapping("/order")
	public void order() {
	}

	@GetMapping("/orders")
	public void listOrders() {
	}
}
