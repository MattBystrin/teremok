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

@Entity
@Table(name = "clean_queue")
public class CleanTaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Column(nullable = false)
	// private LocalDate date;
	//
	// @Column(nullable = false)
	// private boolean ready = false;
	//
	// @ManyToOne
	// @JoinColumn(name = "room", nullable = false)
	// private RoomEntity room;
	//
	// @ManyToOne
	// @JoinColumn(name = "employee", nullable = false)
	// private EmployeeEntity employee;
	//
	// public Long getId() {
	// 	return id;
	// }
	//
	// public void setId(Long id) {
	// 	this.id = id;
	// }
	//
	//
	// public RoomEntity getRoom() {
	// 	return room;
	// }
	//
	// public void setRoom(RoomEntity room) {
	// 	this.room = room;
	// }
	//
	// public EmployeeEntity getEmployee() {
	// 	return employee;
	// }
	//
	// public void setEmployee(EmployeeEntity employee) {
	// 	this.employee = employee;
	// }
	//
	// public LocalDate getDate() {
	// 	return date;
	// }
	//
	// public void setDate(LocalDate date) {
	// 	this.date = date;
	// }
	//
	// public boolean isReady() {
	// 	return ready;
	// }
	//
	// public void setReady(boolean ready) {
	// 	this.ready = ready;
	// }
};
