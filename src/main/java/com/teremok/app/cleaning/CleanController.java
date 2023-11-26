package com.teremok.app.cleaning;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cleaning")
public class CleanController {

	// @GetMapping("/clean/queue")
	// public Iterable<CleanTask> clean(@RequestParam Long employee) {
	// 	return clean_service.getCleanQueue();
	// }

	// @PostMapping("/clean/report")
	// public ResponseEntity send_report(@RequestBody CleanReportModel report) {
	// 	try {
	// 		clean_service.saveReport(report);
	// 		return ResponseEntity.ok("Report saved");
	// 	} catch (Exception e) {
	// 		e.printStackTrace(System.out);
	// 		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	// 	}
	// }
	//
	// @GetMapping("/furniture")
	// public Iterable<FurnitureModel> getFurniture(@RequestParam Long room)
	// {
	// 	return furniture_service.getFurniture(room);
	// }

}
