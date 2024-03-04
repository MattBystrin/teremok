package com.teremok.app.spa;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teremok.app.user.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/spa")
@RequiredArgsConstructor
public class SpaController{

	private final SpaService spaService;

	@GetMapping("/types")
	public void getSpaTypes() {
		return;
	}

	@GetMapping("/available/{date}/{time}")
	public Iterable<SpaType> getAvailable(
		@PathVariable LocalDate date,
		@PathVariable LocalTime time,
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		Long specie = user.getSpecie().getId();

		return spaService.getAvailable(specie, date, time);
	}

	@GetMapping("/records")
	public Iterable<SpaRecordDTO> getRecords() {
		return spaService.getRecords();
	}

	@GetMapping("/records/self")
	public Iterable<SpaRecordDTO> getRecordsSelf(
		Principal principal
	) {
		User user = User.fromPrincipal(principal);
		return spaService.getRecordsSelf(user);
	}

	@PostMapping("/book/{type}/{date}/{time}")
	public ResponseEntity<?> bookSpa(
		@PathVariable Long type,
		@PathVariable LocalDate date,
		@PathVariable LocalTime time,
		Principal principal
	) {
		User user = User.fromPrincipal(principal);

		try {
			spaService.bookSpa(user, type, date, time);
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
}
