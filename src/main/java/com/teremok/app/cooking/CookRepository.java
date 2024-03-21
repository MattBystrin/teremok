package com.teremok.app.cooking;

import org.springframework.data.repository.CrudRepository;

import com.teremok.app.user.User;

public interface CookRepository extends CrudRepository<CookTask, Long> {
	Iterable<CookTask> findByGuest(User guest);
	Iterable<CookTask> findByEmployee(User empl);
}
