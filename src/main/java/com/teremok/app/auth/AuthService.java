package com.teremok.app.auth;

import com.teremok.app.user.User;
import com.teremok.app.user.Role;
import com.teremok.app.user.UserService;
import com.teremok.app.auth.token.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	private final UserService userService;

	public AuthResponse register(RegisterRequest request, Role role) {
		User user = userService.addUser(request, role); 
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(user, jwtToken);
		return AuthResponse.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	public AuthResponse authenticate(AuthRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPass()
			)
		);
		User user = userService.getByEmail(request.getEmail());
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthResponse.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	public void changePassword(PasswdRequest request, User user) {
		if (!userService.checkPassword(user, request.getOldPass())) {
			throw new IllegalStateException("Wrong password");
		}
		if (!request.getNewPass().equals(request.getConfirmPass())) {
			throw new IllegalStateException("Password are not the same");
		}

		String pass = request.getNewPass();

		userService.updatePassword(user, pass);
	}

	private void saveUserToken(User user, String jwtToken) {
		Token token = Token.builder()
			.user(user)
			.token(jwtToken)
			.tokenType(TokenType.BEARER)
			.expired(false)
			.revoked(false)
			.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(User user) {
		List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	public void refreshToken(
		HttpServletRequest request,
		HttpServletResponse response
	) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;

		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			return;
		}

		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			User user = userService.getByEmail(userEmail);
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = AuthResponse.builder()
					.accessToken(accessToken)
					.refreshToken(refreshToken)
					.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}
}
