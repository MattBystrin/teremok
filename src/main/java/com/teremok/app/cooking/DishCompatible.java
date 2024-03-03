package com.teremok.app.cooking;

import com.teremok.app.hostel.species.Specie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dish_compatible")
public class DishCompatible {
	@Id
	@ManyToOne
	private Specie specie;

	@Id
	@ManyToOne
	private Dish dish;
}
