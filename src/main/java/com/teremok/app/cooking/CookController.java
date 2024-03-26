package com.teremok.app.cooking;

import java.security.Principal;
import java.util.List;

import com.teremok.app.user.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cooking")
@RequiredArgsConstructor
public class CookController {

	private final CookService cookService;

	@GetMapping("/menu")
	public Iterable<Dish> getMenu(
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		return cookService.getMenu(user);
	}

	@PostMapping("/order")
	public ResponseEntity<?> order(
		@RequestBody OrderDTO order,
		Principal principal
	) {
		User user = User.fromPrincipal(principal);

		try {
			cookService.order(user, order);
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@GetMapping("/orders")
	public Iterable<CookTaskDTO> getOrders() {
		return cookService.getAllOrders();
	}

	@GetMapping("/orders/self")
	public Iterable<CookTaskDTO> getOrders(
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		return cookService.getOrders(user);
	}

	@PatchMapping("/orders/{id}")
	public ResponseEntity<?> markDone(@PathVariable Long id) {
		cookService.markDone(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
