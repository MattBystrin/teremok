package com.teremok.app.spa;

import java.time.LocalDate;

import com.teremok.app.user.User;

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
@Table(name = "spa_orders")
public class SpaOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private SpaType type;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User employee;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User guest;

	private LocalDate date;
}
