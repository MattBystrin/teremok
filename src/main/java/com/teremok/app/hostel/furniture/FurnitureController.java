package com.teremok.app.hostel.furniture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/furniture")
@RequiredArgsConstructor
public class FurnitureController {

	private final FurnitureService service;

	@GetMapping("/{id}")
	public Furniture getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@GetMapping("/room/{room}")
	public Iterable<Furniture> getByRoom(@PathVariable Long room) {
		return service.getByRoom(room);
	}
};
