package com.teremok.app.hostel.rooms;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "room_types")
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String label;
	private Long cost;
	private Long capacity;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	private List<Room> rooms;
};
