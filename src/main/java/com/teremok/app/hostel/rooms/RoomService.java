package com.teremok.app.hostel.rooms;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository repository;

	public Iterable<RoomType> findAvailable(Long specie, LocalDate arrival, LocalDate departure) {
		Iterable<Room> rooms = repository.findAvailable(specie, arrival, departure);
		HashSet<RoomType> types = new HashSet<>();
		for (Room room : rooms) {
			types.add(room.getType());
		}
		return types;
	}

	public Iterable<Room> findAvailableRoom(Long specie, LocalDate arrival, LocalDate departure) {
		Iterable<Room> rooms = repository.findAvailable(specie, arrival, departure);
		return rooms;
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
