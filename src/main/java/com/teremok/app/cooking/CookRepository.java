package com.teremok.app.cooking;

import org.springframework.data.repository.CrudRepository;

public interface CookRepository extends CrudRepository<CookOrder, Long> {
}
