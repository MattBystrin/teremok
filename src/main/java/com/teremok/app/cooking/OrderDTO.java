package com.teremok.app.cooking;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Position {
		private Long dish;
		private Long quantity;
	}

	private List<Position> positions;
}
