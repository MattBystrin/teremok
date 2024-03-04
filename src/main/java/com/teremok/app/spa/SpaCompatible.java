package com.teremok.app.spa;

import com.teremok.app.hostel.species.Specie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "spa_compatible")
public class SpaCompatible {
	@Id
	@ManyToOne
	private SpaType type;

	@Id
	@ManyToOne
	private Specie specie;
}
