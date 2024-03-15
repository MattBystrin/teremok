package com.teremok.app.cleaning;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CleaningTaskDTO {
	private Long id;
	private LocalDate date;
	private Boolean ready;
	private Long room;
	private Long uid;

	public static CleaningTaskDTO fromTask(CleaningTask task) {
		return new CleaningTaskDTO(
			task.getId(),
			task.getDate(),
			task.getReady(),
			task.getRoom().getId(),
			task.getUser().getId()
		);
	}
}
