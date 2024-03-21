package com.teremok.app.user;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/employees")
	public Iterable<UserDTO> getEmployees() {
		return userService.getEmployees();
	}

	@PostMapping("/employees/add")
	public ResponseEntity<?> addEmployee(
		@RequestBody AddRequest request
	){
		userService.addUser(request, request.getRole());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/roles")
	public List<String> getRoles() {
		return Stream.of(Role.values())
			.filter(role -> role != Role.GUEST && role != Role.USER)
			.map(Role::name)
			.collect(Collectors.toList());
	}

	@GetMapping("/self")
	public UserDTO getSelf(
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		return UserDTO.fromUser(user);
	}
}
