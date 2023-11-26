package com.teremok.app;

import com.teremok.app.user.Role;
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
	public CommandLineRunner cmdRunner(AuthService service) {
		return args -> {
			var admin = RegisterRequest.builder()
				.firstname("Admin")
				.lastname("Adminov")
				.role(Role.ADMIN)
				.pass("root")
				.email("admin@mail.ru")
				.build();

			System.out.println("Admin token: " + service.register(admin).getAccessToken());
		};
	}
}
