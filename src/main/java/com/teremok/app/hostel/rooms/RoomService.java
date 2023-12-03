package com.teremok.app.hostel.rooms;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository repository;

	public Iterable<Room> findAvailable(Long specie, LocalDate arrival, LocalDate departure) {
		return repository.findAvailable(specie, arrival, departure);
	}

	public Boolean isCompatible(Long room, Long specie) {
		return repository.isCompatible(room, specie);
	}

	public Iterable<Room> getRooms() {
		return repository.findAll();
	}

	public Room getRoom(Long room) {
		return repository.findById(room).get();
	}
};
