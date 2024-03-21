package com.teremok.app.cooking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long> {
	@Query(
		value =
		"select * from dishes where dishes.id in (select dish_id from dish_compatible where specie_id = :specie)",
		nativeQuery = true
	)
	Iterable<Dish> findCompatible(Long specie);
}
