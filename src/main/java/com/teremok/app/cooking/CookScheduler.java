package com.teremok.app.cooking;

import org.springframework.stereotype.Service;

import com.teremok.app.user.Role;
import com.teremok.app.user.User;
import com.teremok.app.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CookScheduler {

	private final UserService userService;

	public User getNext() {
		return userService.getByRole(Role.COOK)
			.iterator()
			.next();
	}
}
