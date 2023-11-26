package com.example.teremok.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "book_records")
//@Table(name = "book_records", uniqueConstraints = { @UniqueConstraint(columnNames = { "arrival", "departure", "room" }) })
public class BookRecordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate arrival;
	@Column(nullable = false)
	private LocalDate departure;

	// @ManyToOne
	// @JoinColumn(name = "room", nullable = false)
	// private RoomEntity room;
	//
	// @ManyToOne
	// @JoinColumn(name = "uid", nullable = false)
	// private UserEntity uid;
	//
	// public RoomEntity getRoom() {
	// 	return room;
	// }
	// public void setRoom(RoomEntity room) {
	// 	this.room = room;
	// }
	// public UserEntity getUid() {
	// 	return uid;
	// }
	// public void setUid(UserEntity uid) {
	// 	this.uid = uid;
	// }
	// public Long getId() {
	// 	return id;
	// }
	// public void setId(Long id) {
	// 	this.id = id;
	// }
	// public LocalDate getArrival() {
	// 	return arrival;
	// }
	// public void setArrival(LocalDate arrival_date) {
	// 	this.arrival = arrival_date;
	// }
	// public LocalDate getDeparture() {
	// 	return departure;
	// }
	// public void setDeparture(LocalDate departure_date) {
	// 	this.departure = departure_date;
	// }
};
