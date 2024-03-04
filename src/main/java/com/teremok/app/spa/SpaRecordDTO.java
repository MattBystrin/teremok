package com.teremok.app.spa;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpaRecordDTO {
	private Long id;
	private Long guest;
	private Long empl;
	private LocalDate date;
	private LocalTime time;
	private Long type;

	public static SpaRecordDTO fromRecord(SpaRecord record) {
		return new SpaRecordDTO(
			record.getId(),
			record.getGuest().getId(),
			record.getEmpl().getId(),
			record.getDate(),
			record.getTime(),
			record.getType().getId()
		);
	}
}
