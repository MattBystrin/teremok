package com.teremok.app.booking;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teremok.app.user.User;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping("/reserve")
	public ResponseEntity<HttpStatus> reserve(
		@RequestBody BookRequest book_record,
		Principal principal
	) throws Exception {
		User user = User.fromPrincipal(principal);
		try {
			bookingService.reserveBook(book_record, user);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
