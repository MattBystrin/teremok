package com.teremok.app.booking;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.teremok.app.hostel.rooms.Room;
import com.teremok.app.hostel.rooms.RoomService;
import com.teremok.app.notify.NotificationService;
import com.teremok.app.user.Role;
import com.teremok.app.user.User;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	private final BookRecordRepository bookRecordRepository;
	private final RoomService roomService;
	private final NotificationService notificationService;
	
	@Transactional
	public BookRecord reserveBook(BookRequest request, User user) throws Exception {
		LocalDate arrival = request.getArrival();
		LocalDate departure = request.getDeparture();
		Long specie = user.getSpecie().getId();
		Iterable<Room> rooms = roomService.findAvailableRoom(specie, arrival, departure);
		Room room = null;
		for (Room it : rooms) {
			if (it.getType().getId() == request.getType()) {
				room = it;
				break;
			}
		}

		if (!validateDates(arrival, departure))
			throw new Exception("Ivalid date range");

		if (room == null)
			throw new Exception("No rooms available");

		BookRecord bookRecord = BookRecord.builder()
			.arrival(arrival)
			.departure(departure)
			.room(room)
			.user(user)
			.build();

		notificationService.notfiyRole(Role.ADMIN, "Booking created");
		return bookRecordRepository.save(bookRecord);

	}

	private Boolean validateDates(LocalDate arrival, LocalDate departure) {
		if (arrival.isBefore(LocalDate.now()))
			return false;
		if (departure.isBefore(arrival) || departure.isEqual(arrival))
			return false;

		return true;
	}
};
