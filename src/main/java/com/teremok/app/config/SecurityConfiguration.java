package com.teremok.app.config;

import com.teremok.app.auth.JwtAuthFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.teremok.app.user.Role;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	private static final String[] WHITE_LIST_URL = {
		"/api/v1/auth/**",
	};

	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors(AbstractHttpConfigurer::disable)
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(req ->
			req.requestMatchers(WHITE_LIST_URL)
			.permitAll()
			.requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(Role.ADMIN.name())
			.requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(Role.ADMIN.name())
			.requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(Role.ADMIN.name())
			.requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(Role.ADMIN.name())
			.anyRequest()
			.authenticated()
		)
		.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.logout(logout ->
			logout.logoutUrl("/api/v1/auth/logout")
				.addLogoutHandler(logoutHandler)
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
		);
		return http.build();
	}
}
