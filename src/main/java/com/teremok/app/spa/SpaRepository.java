package com.teremok.app.spa;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SpaRepository extends CrudRepository<SpaType, Long> {
	@Query(
		value =
		"select * from spa_types where spa_types.id in (select type_id from spa_compatible where specie_id = ?1);",
		nativeQuery = true
	)
	Iterable<SpaType> findCompatible(Long specie);

	@Query(
		value ="""
		select * from spa_types where spa_types.id in (select type_id from spa_compatible where specie_id = :specie) \s
		and spa_types.id not in (select type_id from spa_records as sr where \s
			sr.date = :date and (:time != sr.time)); \s
		""",
		nativeQuery = true
	)
	Iterable<SpaType> findAvailable(Long specie, LocalDate date, LocalTime time);
}
