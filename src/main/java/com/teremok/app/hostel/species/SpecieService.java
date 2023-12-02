package com.teremok.app.hostel.species;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecieService {
	private final SpecieRepository repository;

	public Iterable<Specie> getSpecies() {
		return repository.findAll();
	};
};
