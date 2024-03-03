package com.teremok.app.notify;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teremok.app.user.User;
import com.teremok.app.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

	private final NotificationService notificationService;
	private final UserService userService;

	@GetMapping()
	public ResponseEntity<Iterable<Notification>> getNotifications(
		Principal principal
	) {
		User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
		System.out.println("User id: " + user.getId().toString());
		Iterable<Notification> notifications = notificationService.getNotifications(user);
		return ResponseEntity.ok(notifications);
	}

	@PostMapping
	public ResponseEntity<?> markNotifications(
		@RequestBody List<Long> ids,
		Principal principal
	) {
		User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
		notificationService.markNotifications(user, ids);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/send/{id}")
	public ResponseEntity<?> sendNotification(@PathVariable Long id) {
		User user = userService.getUser(id);
		notificationService.notify(user, "Some test message");
		return ResponseEntity.ok().build();
	}
}
