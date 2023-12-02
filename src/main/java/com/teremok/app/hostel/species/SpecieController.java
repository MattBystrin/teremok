package com.teremok.app.hostel.species;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/species")
@RequiredArgsConstructor
public class SpecieController {

	private final SpecieService service;

	@GetMapping
	public Iterable<Specie> getSpecies() {
		return service.getSpecies();
	}
};
