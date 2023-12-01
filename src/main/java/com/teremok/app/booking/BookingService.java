package com.teremok.app.booking;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.teremok.app.hostel.rooms.Room;
import com.teremok.app.hostel.rooms.RoomRepository;
import com.teremok.app.user.User;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private final BookRecordRepository bookRecordRepository;
	private final RoomRepository roomRepository;
	
	@Transactional
	public void reserveBook(BookRequest request, User user) throws Exception {
		if (!validateDates(request))
			throw new Exception("Ivalid date range");

		LocalDate arrival = request.getArrival();
		LocalDate departure = request.getDeparture();
		Room room = roomRepository.findById(request.getRoom()).get();

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

	private Boolean validateDates(BookRequest request) {
		LocalDate arrival = request.getArrival();
		LocalDate departure = request.getDeparture();

		if (arrival.isBefore(LocalDate.now()))
			return false;
		if (departure.isBefore(arrival) || departure.isEqual(arrival))
			return false;

		return true;
	}
};
