package com.teremok.app.cleaning;

import com.teremok.app.user.UserService;

import org.springframework.stereotype.Service;

import com.teremok.app.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Scheduler {
	private final UserService userService;

	// TODO: find all cleaners and find one with minimal tasks
	public User assignCleaning() {
		User user = userService.getUser(1L);
		return user;
	}
}
