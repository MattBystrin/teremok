package com.teremok.app.hostel.rooms;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
	@Query(
		value =
		"select * from rooms where rooms.type in (select room_type from room_compatible where specie = ?1);",
		nativeQuery = true
	)
	List<Room> findCompatible(Long specie);

	@Query(
		value = 
		"select * from rooms where rooms.type in (select room_type from specie_to_room_type where specie = ?1)" + 
		" and rooms.id not in (select room from book_records as br where ?2 between br.arrival and br.departure" + 
		" or ?3 between br.arrival and br.departure or (br.arrival > ?2 and br.departure < ?3 ) );" ,
		nativeQuery = true
	)
	List<Room> findFree(Long specie, LocalDate arrival, LocalDate departure);
}
