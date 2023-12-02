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
	public Furniture findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@GetMapping("/room/{room}")
	public Iterable<Furniture> findByRoom(@PathVariable Long room) {
		return service.findByRoom(room);
	}
};
