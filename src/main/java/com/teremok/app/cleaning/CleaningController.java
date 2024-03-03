package com.teremok.app.cleaning;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cleaning")
public class CleaningController {

	private final CleaningService cleaningService;

	@GetMapping("/queue")
	public Iterable<CleaningTask> getQueue() {
		return cleaningService.getQueue();
	}

	@PostMapping("/queue")
	public ResponseEntity<String> addTask() {
		cleaningService.addTask(1L);
		return ResponseEntity.ok("Task added");
	}

	@GetMapping("/queue/employee/{id}")
	public Iterable<CleaningTask> getEmployeeQueue(@PathVariable Long id) {
		return cleaningService.getEmployeeQueue(id);
	}

	@PostMapping("/report")
	public ResponseEntity<?> saveReport(@RequestBody ReportDTO report) {
		try {
			cleaningService.saveReport(report);
			return ResponseEntity.ok("Report saved");
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}
