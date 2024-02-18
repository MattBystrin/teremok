package com.teremok.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teremok.app.user.*;
import com.teremok.app.auth.RegisterRequest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeTests {

	@Test
	void LoadContext() {
	}

	@Autowired
	private UserService userService;

	@Test
	void addEmployee() {

		RegisterRequest request = RegisterRequest.builder()
			.firstname("Tester")
			.lastname("Testerov")
			.email("testerov@mail.com")
			.pass("pass")
			.specie(1L)
			.build();

		assertDoesNotThrow(() ->
			userService.addUser(request, Role.CLEANER)
		);
	}

	@Test
	void wrongCredentials() {
		RegisterRequest request = RegisterRequest.builder()
			.firstname("Tester")
			.lastname("Testerov")
			.pass("pass")
			.build();

		try {
			userService.addUser(request, Role.CLEANER);
		} catch (Exception e) {
			return;
		}
		assertTrue(false);
	}

	@AfterAll
	void addExisting() {
		RegisterRequest request = RegisterRequest.builder()
			.firstname("Tester")
			.lastname("Testerov")
			.email("testerov@mail.com")
			.pass("pass")
			.specie(1L)
			.build();

		try {
			userService.addUser(request, Role.CLEANER);
		} catch (Exception e) {
			return;
		}
		assertTrue(false);
	}
}
