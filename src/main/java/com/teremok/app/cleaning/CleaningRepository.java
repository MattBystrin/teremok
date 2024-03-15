package com.teremok.app.cleaning;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CleaningRepository extends CrudRepository<CleaningTask, Long> {
	@Query(
		value =
		"select * from clean_queue where ready = false and room = ?1",
		nativeQuery = true
	)
	public CleaningTask findPending(Long room);

	@Query(
		value =
		"select * from clean_tasks where user_id = :uid",
		nativeQuery = true
	)
	public Iterable<CleaningTask> findByUser(Long uid);
}
