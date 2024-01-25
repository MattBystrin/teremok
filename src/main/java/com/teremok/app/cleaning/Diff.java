package com.teremok.app.cleaning;

import com.teremok.app.hostel.furniture.Furniture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "clean_diffs")
public class Diff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private CleaningReport report;

	@ManyToOne
	private Furniture furniture;

	private Long diff;
}
