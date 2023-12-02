package com.teremok.app.hostel.furniture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teremok.app.hostel.rooms.Room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "furniture")
public class Furniture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long state;

	@ManyToOne
	@JoinColumn(name = "type", nullable = false)
	private FurnitureType type;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "room", nullable = false)
	private Room room;
};
