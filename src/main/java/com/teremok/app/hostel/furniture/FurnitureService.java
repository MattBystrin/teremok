package com.teremok.app.hostel.furniture;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FurnitureService {
	private final FurnitureRepository repository;

	public Iterable<Furniture> getByRoom(Long room) {
		return repository.findByRoom(room);
	}

	public Furniture getById(Long id) {
		return repository.findById(id).get();
	}

	public void updateState(Long id, Long diff) {
		Furniture furniture = repository.findById(id).get();
		furniture.setState(furniture.getState() - diff);
		repository.save(furniture);
	}
};
