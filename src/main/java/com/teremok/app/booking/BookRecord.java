package com.teremok.app.booking;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import com.teremok.app.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teremok.app.hostel.rooms.Room;

//@Table(name = "book_records", uniqueConstraints = { @UniqueConstraint(columnNames = { "arrival", "departure", "room" }) })
@Data
@Builder
@Entity
@Table(name = "book_records")
public class BookRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate arrival;
	@Column(nullable = false)
	private LocalDate departure;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room", nullable = false)
	@JsonIgnore
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	@JsonIgnore
	private User user;
};
