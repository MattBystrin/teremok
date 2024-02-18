package com.teremok.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teremok.app.booking.BookRequest;
import com.teremok.app.booking.BookingService;
import com.teremok.app.user.User;
import com.teremok.app.user.UserRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingTests {

	@Test
	void LoadContext() {
	}

	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserRepository userRepository;

	@Test
	void correctRequest() {
		LocalDate arrival = LocalDate.parse("2025-02-20");
		LocalDate departure = LocalDate.parse("2025-02-25");
		BookRequest request = new BookRequest(arrival, departure, 1L);
		User user = userRepository.findById(1L).get();
		assertDoesNotThrow(() ->
			bookingService.reserveBook(request, user)
		);
	}

	@Test
	void invalidArrival() {
		LocalDate arrival = LocalDate.parse("2020-01-20");
		LocalDate departure = LocalDate.parse("2025-02-25");
		BookRequest request = new BookRequest(arrival, departure, 1L);
		User user = userRepository.findById(1L).get();
		try {
			bookingService.reserveBook(request, user);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Ivalid date range");
			return;
		}
		assertTrue(false);
	}

	@Test
	void invalidDeparutre() {
		LocalDate arrival = LocalDate.parse("2025-03-20");
		LocalDate departure = LocalDate.parse("2025-02-25");
		BookRequest request = new BookRequest(arrival, departure, 1L);
		User user = userRepository.findById(1L).get();
		try {
			bookingService.reserveBook(request, user);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Ivalid date range");
			return;
		}
		assertTrue(false);
	}

	@AfterAll
	void overlappingBook() {
		LocalDate arrival = LocalDate.parse("2025-02-23");
		LocalDate departure = LocalDate.parse("2025-02-28");
		BookRequest request = new BookRequest(arrival, departure, 1L);
		User user = userRepository.findById(1L).get();
		try {
			bookingService.reserveBook(request, user);
		} catch (Exception e) {
			return;
		}
		assertTrue(false);
	}
}
