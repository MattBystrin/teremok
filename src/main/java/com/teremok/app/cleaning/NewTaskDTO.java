package com.teremok.app.cleaning;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewTaskDTO {
	private LocalDate date;
	private Long room;
}
