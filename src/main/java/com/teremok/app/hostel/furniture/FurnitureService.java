package com.teremok.app.hostel.furniture;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FurnitureService {
	private final FurnitureRepository repository;

	public Iterable<Furniture> findByRoom(Long room) {
		return repository.findByRoom(room);
	}

	public Furniture findById(Long furniture) {
		return repository.findById(furniture).get();
	}
};
