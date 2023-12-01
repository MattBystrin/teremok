package com.teremok.app.booking;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BookRecordRepository extends CrudRepository<BookRecord, Long> {
	@Query(
		value  ="""
		select :room not in (select room from book_records as br where\s
			(:arrival >= br.arrival and :arrival < br.departure) or \s
			(:departure > br.arrival and :departure <= br.departure) or \s 
			(br.arrival >= :arrival and br.departure <= :departure)) \s
		""",
		nativeQuery = true
	)
	Boolean isRoomAvailable(LocalDate arrival, LocalDate departure, Long room);
}
