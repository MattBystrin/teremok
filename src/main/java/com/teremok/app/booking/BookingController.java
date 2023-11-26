package com.teremok.app.booking;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
	
	/* @GetMapping("/rooms")
	public Iterable<RoomModel> get_rooms(
		@RequestParam("specie") Long specie_id,
		@RequestParam("arrival") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate arrival,
		@RequestParam("departure") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate departure
	)
	{
		return bookService.getFreeRooms(specie_id, arrival, departure);
	} */

	@GetMapping("/species")
	public ResponseEntity<String> get_species() {
		return ResponseEntity.ok("Here your cool species\n");
	}

	/* @PostMapping("/book")
	public ResponseEntity<HttpStatus> book(@RequestBody BookRecordModel book_record)
	{
		try {
			bookService.reserveBook(book_record);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/notify")
	public Iterable<NotificationModel> getNotifications(@RequestParam("user") Long user) {
		return notification_service.getNotifications(user);
	} */
}
