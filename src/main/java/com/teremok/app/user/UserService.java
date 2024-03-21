package com.teremok.app.user;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teremok.app.auth.RegisterRequest;
import com.teremok.app.hostel.species.Specie;
import com.teremok.app.hostel.species.SpecieRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final SpecieRepository specieRepository;
	private final PasswordEncoder passwordEncoder;

	private List<UserDTO> mapToDTO(Iterable<User> users) {
		List<UserDTO> dtos = StreamSupport.stream(users.spliterator(), false)
			.map(user -> UserDTO.fromUser(user))
			.collect(Collectors.toList());
		return dtos;
	}

	public User updatePassword(User user, String pass) {
		user.setPass(passwordEncoder.encode(pass));
		return repository.save(user);
	}

	public User addUser(RegisterRequest request, Role role) {
		Specie specie = specieRepository.findById(request.getSpecie()).get();
		String password = passwordEncoder.encode(request.getPass());
		User user = User.builder()
			.firstname(request.getFirstname())
			.lastname(request.getLastname())
			.email(request.getEmail())
			.specie(specie)
			.pass(password)
			.role(role)
			.build();

		return repository.save(user);
	}

	public User getUser(Long id) {
		return repository.findById(id).get();
	}

	public User getByEmail(String email) {
		return repository.findByEmail(email).get();
	}

	public Iterable<UserDTO> getEmployees() {
		List<UserDTO> employees = mapToDTO(getByRole(Role.ADMIN));
		employees.addAll(mapToDTO(getByRole(Role.COOK)));
		employees.addAll(mapToDTO(getByRole(Role.SPA)));
		employees.addAll(mapToDTO(getByRole(Role.CLEANER)));
		return employees;
	}

	public Iterable<User> getByRole(Role role) {
		return repository.findByRole(role);
	}

	public boolean checkPassword(User user, String pass) {
		String upass = user.getPass();

		if (upass.equals(passwordEncoder.encode(pass)))
			return true;

		return false;
	}
}
