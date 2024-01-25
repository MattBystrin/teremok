package com.teremok.app.cleaning;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import com.teremok.app.user.User;
import com.teremok.app.hostel.rooms.Room;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "clean_tasks")
public class CleaningTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	@Builder.Default
	private Boolean ready = false;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Room room;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
};
