package com.teremok.app.cleaning;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
	private Long task;
	private String comment;

	@Data
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ReportDiff {
		private Long id;
		private Long diff;
	}

	private List<ReportDiff> diffs;
}
