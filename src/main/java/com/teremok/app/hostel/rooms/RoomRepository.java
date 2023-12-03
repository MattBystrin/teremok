package com.teremok.app.hostel.rooms;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
	@Query(
		value =
		"select * from rooms where rooms.type in (select room_type from room_compatible where specie = ?1);",
		nativeQuery = true
	)
	Iterable<Room> findCompatible(Long specie);

	@Query(
		value = """
		select :room exist in (select id from rooms where rooms.type in (select room_type from room_compatible where specie = :specie));
		""",
		nativeQuery = true
	)
	Boolean isCompatible(Long room, Long specie);

	@Query(
		value = """
		select * from rooms where rooms.type in (select room_type from room_compatible where specie = :specie) \s
		and rooms.id not in (select room from book_records as br where \s
			(:arrival >= br.arrival and :arrival < br.departure) or \s
			(:departure > br.arrival and :departure <= br.departure) or \s 
			(br.arrival >= :arrival and br.departure <= :departure)); \s
		""",
		nativeQuery = true
	)
	Iterable<Room> findAvailable(Long specie, LocalDate arrival, LocalDate departure);
}
