package com.teremok.app.cleaning;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CleanRepository extends CrudRepository<CleanTaskEntity, Long> {
	@Query(
		value =
		"select * from clean_queue where ready = false and room = ?1",
		nativeQuery = true
	)
	public CleanTaskEntity findPending(Long room);
}
