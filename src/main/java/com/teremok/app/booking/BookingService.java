package com.teremok.app.booking;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.teremok.app.hostel.rooms.Room;
import com.teremok.app.hostel.rooms.RoomRepository;
import com.teremok.app.hostel.rooms.RoomService;
import com.teremok.app.user.User;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private final BookRecordRepository bookRecordRepository;
	private final RoomService roomService;
	
	@Transactional
	public void reserveBook(BookRequest request, User user) throws Exception {
		LocalDate arrival = request.getArrival();
		LocalDate departure = request.getDeparture();
		Room room = roomService.getRoom(request.getRoom());
		

		if (!validateDates(arrival, departure))
			throw new Exception("Ivalid date range");

		// if (roomService.isCompatible(request.getRoom(), user.getSpecie().getId()))
		// 	throw new Exception("User not allowed to book this room");

		BookRecord bookRecord = BookRecord.builder()
			.arrival(arrival)
			.departure(departure)
			.room(room)
			.user(user)
			.build();

		if (bookRecordRepository.isRoomAvailable(arrival, departure, request.getRoom()))
			bookRecordRepository.save(bookRecord);
		else
			throw new Exception("Room is unavailable");
	}

	private Boolean validateDates(LocalDate arrival, LocalDate departure) {
		if (arrival.isBefore(LocalDate.now()))
			return false;
		if (departure.isBefore(arrival) || departure.isEqual(arrival))
			return false;

		return true;
	}
};
