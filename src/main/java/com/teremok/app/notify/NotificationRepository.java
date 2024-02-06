package com.teremok.app.notify;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
	@Query(
		value = """
		select * from notifications where user_id = :id and viewed = false
		""",
		nativeQuery = true
	)
	Iterable<Notification> findByUser(Long id);
};
