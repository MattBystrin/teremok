package com.teremok.app.cleaning;

import com.teremok.app.user.UserService;

import org.springframework.stereotype.Service;

import com.teremok.app.user.Role;
import com.teremok.app.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Scheduler {
	private final UserService userService;

	public User getNext() {
		User user = userService.getByRole(Role.CLEANER)
			.iterator()
			.next();
		return user;
	}
}
