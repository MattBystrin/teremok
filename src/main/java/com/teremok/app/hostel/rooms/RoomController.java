package com.teremok.app.hostel.rooms;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
	public final RoomService service;

	@GetMapping
	public Iterable<Room> getRooms() {
		return service.getRooms();
	}

	@GetMapping("/{room}")
	public Room getRoom(@PathVariable Long room) {
		return service.getRoom(room);
	}

	@GetMapping("/available/{specie}/{arrival}/{departure}")
	public Iterable<Room> findAvailable(
		@PathVariable Long specie, 
		@PathVariable LocalDate arrival,
		@PathVariable LocalDate departure)
	{
		return service.findAvailable(specie, arrival, departure);
	}
};
