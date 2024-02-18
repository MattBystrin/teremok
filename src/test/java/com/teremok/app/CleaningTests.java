package com.teremok.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teremok.app.cleaning.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CleaningTests {
	@Test
	void LoadContext() {
	}

	@Autowired
	CleaningService cleaningService;

	@Test
	@Order(1)
	void saveReport() {
		List<ReportDTO.ReportDiff> diff = Arrays.asList(
			new ReportDTO.ReportDiff(1L, 1L),
			new ReportDTO.ReportDiff(1L, 1L)
		);
		ReportDTO report = new ReportDTO(1L, "Ok", diff);

		assertDoesNotThrow( () ->
			cleaningService.saveReport(report)
		);
	}

	@Test
	@Order(2)
	void emptyDiffs() {
		List<ReportDTO.ReportDiff> diff = Arrays.asList();
		ReportDTO report = new ReportDTO(1L, "Ok", diff);

		try {
			cleaningService.saveReport(report);
		} catch (Exception e) {
			return;
		}
		assertTrue(false);

	}

	@Test
	@Order(3)
	void wrongId() {
		List<ReportDTO.ReportDiff> diff = Arrays.asList(
			new ReportDTO.ReportDiff(1L, 1L),
			new ReportDTO.ReportDiff(2L, 1L)
		);
		ReportDTO report = new ReportDTO(-1L, "Ok", diff);

		try {
			cleaningService.saveReport(report);
		} catch (Exception e) {
			return;
		}
	}
}
