package com.teremok.app.spa;

import org.springframework.data.repository.CrudRepository;

import com.teremok.app.user.User;

public interface SpaRecordRepository extends CrudRepository<SpaRecord, Long> {
	Iterable<SpaRecord> findByEmpl(User empl);
	Iterable<SpaRecord> findByGuest(User guest);
}
