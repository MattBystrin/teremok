package com.teremok.app.cleaning;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clean_reports")
public class CleanReportEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private String comment;
	//
	// private Long room;
	// 
	// public Long getRoom() {
	// 	return room;
	// }
	//
	// public void setRoom(Long room) {
	// 	this.room = room;
	// }
	//
	// public Long getId() {
	// 	return id;
	// }
	//
	// public void setId(Long id) {
	// 	this.id = id;
	// }
	//
	// public String getComment() {
	// 	return comment;
	// }
	//
	// public void setComment(String comment) {
	// 	this.comment = comment;
	// }
}
