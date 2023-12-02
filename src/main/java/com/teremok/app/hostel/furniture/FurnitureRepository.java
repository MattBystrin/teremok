package com.teremok.app.hostel.furniture;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FurnitureRepository extends CrudRepository<Furniture, Long> {
	@Query(value = "select * from furniture where room = :room", nativeQuery = true)
	public Iterable<Furniture> findByRoom(Long room);
}
