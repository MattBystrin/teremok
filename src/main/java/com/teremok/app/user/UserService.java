package com.teremok.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;

	public void changePassword(PasswdRequest request, Principal connectedUser) {

		var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

		// check if the current password is correct
		if (!passwordEncoder.matches(request.getOldPass(), user.getPass())) {
			throw new IllegalStateException("Wrong password");
		}
		// check if the two new passwords are the same
		if (!request.getNewPass().equals(request.getConfirmPass())) {
			throw new IllegalStateException("Password are not the same");
		}

		// update the password
		user.setPass(passwordEncoder.encode(request.getNewPass()));

		// save the new password
		repository.save(user);
	}

	public User getUser(Long id) {
		return repository.findById(id).get();
	}

	public Iterable<User> getByRole(String role) {
		return repository.findByRole(role);
	}
}
