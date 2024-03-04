package com.teremok.app.spa;

import java.time.LocalDate;
import java.time.LocalTime;

import com.teremok.app.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spa_records")
public class SpaRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User guest;

	@ManyToOne
	private User empl;

	private LocalDate date;
	private LocalTime time;

	@ManyToOne
	private SpaType type;
}
