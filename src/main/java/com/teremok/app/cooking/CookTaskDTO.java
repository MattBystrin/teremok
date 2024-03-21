package com.teremok.app.cooking;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CookTaskDTO {
	private Long id;
	private Long empl;
	private Long guest;
	private LocalDate date;
	private Boolean ready;

	public static CookTaskDTO fromTask(CookTask task) {
		return new CookTaskDTO(
			task.getId(),
			task.getEmployee().getId(),
			task.getGuest().getId(),
			task.getDate(),
			task.getReady()
		);
	}
}
