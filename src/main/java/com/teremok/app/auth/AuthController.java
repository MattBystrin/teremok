package com.teremok.app.auth;

import com.teremok.app.user.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(
		@RequestBody RegisterRequest request
	) {
		return ResponseEntity.ok(service.register(request, Role.USER));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(
		@RequestBody AuthRequest request
	) {
		System.out.println("Auth request");
		return ResponseEntity.ok(service.authenticate(request));
	}

	@PostMapping("/refresh-token")
	public void refreshToken(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		service.refreshToken(request, response);
	}

	@PatchMapping("/passwd")
	public ResponseEntity<?> changePassword(
		@RequestBody PasswdRequest request,
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		service.changePassword(request, user);
		return ResponseEntity.ok().build();
	}
}
