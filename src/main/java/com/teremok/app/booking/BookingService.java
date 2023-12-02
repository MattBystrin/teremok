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
		LocalDate arrival = request.getArrival();
		LocalDate departure = request.getDeparture();
		Room room = roomRepository.findById(request.getRoom()).get();

		if (!validateDates(arrival, departure))
			throw new Exception("Ivalid date range");

		// TODO
		// if (!roomRepository.isCompatible(request.getRoom(), user.getSpecie()))
		// 	throw new Exception("Room not compatible with user specie");


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
