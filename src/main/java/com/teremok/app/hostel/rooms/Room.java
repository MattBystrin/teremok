package com.teremok.app.hostel.rooms;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;


@Data
@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "type", nullable = false)
	private RoomType type;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
	// private List<Furniture> furniture;
};
