package com.teremok.app.hostel.rooms;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teremok.app.user.User;

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

	@GetMapping("/available/{arrival}/{departure}")
	public Iterable<RoomType> findAvailable(
		@PathVariable LocalDate arrival,
		@PathVariable LocalDate departure,
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		Long specie = user.getSpecie().getId();
		return service.findAvailable(specie, arrival, departure);
	}
};
