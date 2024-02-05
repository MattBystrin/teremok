package com.teremok.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	@RequestMapping("/employees/add")
	public ResponseEntity<?> addEmployee(
		@RequestBody AddRequest request
	){
		userService.addUser(request, request.getRole());
		return ResponseEntity.ok().build();
	}
}
