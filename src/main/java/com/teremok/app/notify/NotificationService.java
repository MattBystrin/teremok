package com.teremok.app.notify;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teremok.app.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final NotificationRepository repository;

	public Iterable<Notification> getNotifications(User user) {
		return repository.findByUser(user.getId());
	}

	public void markNotifications(User user, Iterable<Long> ids) {
		for (Long id: ids) {
			Optional<Notification> opt = repository.findById(id);
			if (opt.isEmpty())
				continue;

			Notification ntf = opt.get();
			System.out.println("Ntf user " + ntf.getUser().getId());
			if (ntf.getUser().getId() != user.getId())
				continue;

			System.out.println("Setting to true");
			ntf.setViewed(true);
			repository.save(ntf);
		}
	}

	public void send(User user, String message) {
		Notification notification = Notification.builder()
			.user(user)
			.time(LocalTime.now())
			.message(message)
			.build();
		repository.save(notification);
	}
}
