package com.teremok.app;

import com.teremok.app.user.Role;
import com.teremok.app.user.UserService;
import com.teremok.app.auth.AuthService;
import com.teremok.app.auth.RegisterRequest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner cmdRunner(AuthService authService, UserService userService) {
		return args -> {
			var admin = RegisterRequest.builder()
				.firstname("Admin")
				.lastname("Adminov")
				.pass("root")
				.specie(1L)
				.email("admin@mail.ru")
				.build();
			System.out.println("Admin email: admin@mail.ru");
			System.out.println("Admin pass: root");
			System.out.println("Admin token: " + authService.register(admin, Role.ADMIN).getAccessToken());

			var cleaner = RegisterRequest.builder()
				.firstname("Cleaner")
				.lastname("Clinov")
				.pass("clean")
				.email("clean@mail.ru")
				.specie(1L)
				.build();
			userService.addUser(cleaner, Role.CLEANER);

			var spa = RegisterRequest.builder()
				.firstname("Spa")
				.lastname("Spanov")
				.pass("spa")
				.email("spa@mail.ru")
				.specie(1L)
				.build();
			userService.addUser(spa, Role.SPA);

			var cook = RegisterRequest.builder()
				.firstname("Cook")
				.lastname("Cookerov")
				.pass("cook")
				.email("cook@mail.ru")
				.specie(1L)
				.build();
			userService.addUser(cook, Role.COOK);
		};
	}
}
