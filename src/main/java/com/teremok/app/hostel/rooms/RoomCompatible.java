package com.teremok.app.hostel.rooms;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.teremok.app.hostel.species.Specie;

@Entity
@Table(name = "room_compatible")
public class RoomCompatible {
	@Id
	@ManyToOne
	@JoinColumn(name = "room_type")
	private RoomType room_type;

	@Id
	@ManyToOne
	@JoinColumn(name = "specie")
	private Specie specie;
};
