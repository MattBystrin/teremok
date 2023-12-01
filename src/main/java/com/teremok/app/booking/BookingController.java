package com.teremok.app.booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teremok.app.user.User;
import com.teremok.app.user.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
	private final BookingService bookingService;
	private final UserRepository userRepository;

	@PostMapping("/reserve")
	public ResponseEntity<HttpStatus> reserve(@RequestBody BookRequest book_record) throws Exception {
		User user = userRepository.findById(1).get();

		bookingService.reserveBook(book_record, user);

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
